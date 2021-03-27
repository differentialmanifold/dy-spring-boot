package samples.demo.aop;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Date;


/**
 * 记录访问日志
 *
 */
@Aspect
@Component
public class SysLogAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(SysLogAspect.class);

	
	@Pointcut("@annotation(RequestLog)")
	public void requestLogAspect() {
	}
	
	/**
	 * 后置通知，用于记录操作日志
	 * 
	 * @param joinPoint
	 */
	@AfterReturning("requestLogAspect()")
	public void accessLog(JoinPoint joinPoint) {
		try {
			String targetName = joinPoint.getTarget().getClass().getName();
			String methodName = joinPoint.getSignature().getName();
			Object[] arguments = joinPoint.getArgs();
			Class<?> targetClass = Class.forName(targetName);

			Method[] methods = targetClass.getMethods();

			String name = "";
			String description = "";

			for (Method method : methods) {
				if (method.getName().equals(methodName)) {
					Class[] clazzs = method.getParameterTypes();
					if (clazzs.length == arguments.length) {
						name = method.getAnnotation(RequestLog.class).name();
						description = method.getAnnotation(RequestLog.class).description();
						break;
					}
				}
			}

			logger.info("Current time is {}", getDateTime(new Date()));
			logger.info("name is {}", name);
			logger.info("description is {}", description);
		} catch (Exception e) {
			logger.error("系统日志记录错误", e);
		}

	}

	@Around("requestLogAspect()")
	public void around(ProceedingJoinPoint joinPoint) throws Throwable {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class<?> targetClass = Class.forName(targetName);

		Class[] clazz = new Class[arguments.length];
		for (int i = 0; i < arguments.length; i++) {
			clazz[i] = arguments[i].getClass();
		}

		//通过反射获取调用的方法method
		Method method = targetClass.getMethod(methodName, clazz);

		//获取方法的参数
		Parameter[] parameters = method.getParameters();

		//拼接字符串，格式为{参数1:值1,参数2::值2}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < parameters.length; i++) {
			Parameter parameter = parameters[i];
			String name = parameter.getName();
			sb.append(name).append(":").append(arguments[i]).append(",");
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.lastIndexOf(","));
		}
		//执行结果
		Object res;
		try {
			//执行目标方法，获取执行结果
			res = joinPoint.proceed();
			logger.info("调用{}.{}方法成功，参数为[{}]，返回结果[{}]", targetName, methodName, sb.toString(), JSONObject.toJSONString(res));
		} catch (Exception e) {
			logger.error("调用{}.{}方法发生异常", targetName, methodName);
		}
	}

	public static String getDateTime(Date date) {
		java.text.SimpleDateFormat d = new java.text.SimpleDateFormat();
		d.applyPattern("yyyy-MM-dd HH:mm:ss");
		String str_date = d.format(date);
		return str_date;
	}
	



}
