package samples.demo.aop;

import java.lang.annotation.*;

/**
 * 请求日志注解
 *
 */

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestLog {
	
	/** * 方法中文名 */
	String name() default "";

	/** * 方法描述 */
	String description() default "";

}
