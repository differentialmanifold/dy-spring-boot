package club.differentialmanifold.dy.core.spring.boot.autoconfigure.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    /**
     * 处理自定义异常
     *
     */
    @ExceptionHandler(value = CodeException.class)
    @ResponseBody
    public ResponseResult<Void> customErrorHandler(CodeException e) {
        logger.error("customErrorHandler", e);
        return ResponseResult.customError(e);
    }

    /**
     * 处理其他异常
     *
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseResult<Void> exceptionHandler( Exception e) {
        logger.error("exceptionHandler", e);
        return ResponseResult.enumError(CommonCode.INTERNAL_SERVER_ERROR);
    }
}
