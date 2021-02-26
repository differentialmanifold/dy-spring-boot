package club.differentialmanifold.dy.core.spring.boot.autoconfigure.exception;


/**
 * 带状态码的异常
 */
public class CodeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8852431683511060530L;
	
	private Integer errcode;
	private String errmsg;
	
	public CodeException() {}
	public CodeException(Integer errcode) {
        this("errcode: " + errcode);
        this.errcode = errcode;
    }
	public CodeException(Integer errcode, String errmsg) {
	    super(errmsg);
	    this.errcode = errcode;
	    this.errmsg = errmsg;
    }
	public CodeException(ErrorCode err) {
		super(err.getErrmsg());
		this.errcode = err.getErrcode();
		this.errmsg = err.getErrmsg();
	}
	public Integer getErrcode() {
		return errcode;
	}
	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public CodeException(String message) {
        super(message);
    }

    public CodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CodeException(Throwable cause) {
        super(cause);
    }
}
