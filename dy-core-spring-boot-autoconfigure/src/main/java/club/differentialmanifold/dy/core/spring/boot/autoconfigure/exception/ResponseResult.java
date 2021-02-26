package club.differentialmanifold.dy.core.spring.boot.autoconfigure.exception;


import java.io.Serializable;


/**
 * 接口返回数据
 */
public class ResponseResult<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1206929290920959440L;

    private Integer code = -1;
    private String msg = "";
    private T data = null;

    public ResponseResult() {
    }

    public ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseResult(Integer code, String msg) {
        this(code, msg, null);
    }

    public ResponseResult(ErrorCode resultEnum, T data) {
        this(resultEnum.getErrcode(), resultEnum.getErrmsg(), data);
    }

    public ResponseResult(ErrorCode resultEnum) {
        this(resultEnum.getErrcode(), resultEnum.getErrmsg());
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 成功
     *
     * @return
     */
    public static <T> ResponseResult<T> success() {
        return success(null);
    }

    /**
     * 成功
     * @param data
     * @return
     */
    public static <T> ResponseResult<T> success(T data) {
        ResponseResult<T> rb = new ResponseResult<T>();
        rb.setCode(CommonCode.SUCCESS.getErrcode());
        rb.setMsg(CommonCode.SUCCESS.getErrmsg());
        rb.setData(data);
        return rb;
    }

    /**
     * 失败
     */
    public static <T> ResponseResult<T> error(Integer code, String message) {
        ResponseResult<T> rb = new ResponseResult<T>();
        rb.setCode(code);
        rb.setMsg(message);
        rb.setData(null);
        return rb;
    }

    /**
     * 失败
     */
    public static <T> ResponseResult<T> error(String message) {
        ResponseResult<T> rb = new ResponseResult<T>();
        rb.setCode(CommonCode.FAIL.getErrcode());
        rb.setMsg(CommonCode.FAIL.getErrmsg());
        rb.setData(null);
        return rb;
    }

    //自定义异常返回的结果
    public static <T> ResponseResult<T> customError(CodeException de){
        ResponseResult<T> result = new ResponseResult<T>();
        result.setCode(de.getErrcode());
        result.setMsg(de.getErrmsg());
        result.setData(null);
        return result;
    }
    //其他异常处理方法返回的结果
    public static <T> ResponseResult<T> enumError(ErrorCode errorEnum){
        ResponseResult<T> result = new ResponseResult<T>();
        result.setCode(errorEnum.getErrcode());
        result.setMsg(errorEnum.getErrmsg());
        result.setData(null);
        return result;
    }

}
