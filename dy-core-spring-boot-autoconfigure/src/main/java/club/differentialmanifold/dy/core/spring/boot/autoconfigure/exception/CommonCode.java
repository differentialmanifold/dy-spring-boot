package club.differentialmanifold.dy.core.spring.boot.autoconfigure.exception;


/**
 * 全局错误
 */
public enum CommonCode implements ErrorCode {
	
	FAIL(-1, "fail"), // 请求失败
	SUCCESS(0, "success"), // 请求成功

	// 1000+ 通用异常
	INVALID_PARAMTER(1001, "invalid parameter"), // 错误的参数
    CONCURRENCY_FAILURE(1002, "concurrency failure"), // 数据并发异常
    ACCESS_DENIED(1003, "access denied"), // 访问拒绝
    VALIDATION_FAILURE(1004, "validation failure"), // 数据校验失败
    METHOD_NOT_SUPPORTED(1005, "method not supported"), // http方法不支持
    INTERNAL_SERVER_ERROR(1006, "internal server error"), // 系统内部异常
    INVALID_ACCESSTOKEN(1007,"Invalid AccessToken"), // TOKEN无效
    INVALID_SIGNATURE(1008,"Invalid Signature"), // 签名无效
	UNIQUE_KEY_CONFLICTS(1009, "unique key conflicts"); //唯一键冲突
	
	private final Integer errcode;
	private final String errmsg;

    CommonCode(Integer errcode, String errmsg) {
		this.errcode = errcode;
		this.errmsg = errmsg;
	}

	@Override
	public Integer getErrcode() {
		return errcode;
	}

    @Override
	public String getErrmsg() {
		return errmsg;
	}

}
