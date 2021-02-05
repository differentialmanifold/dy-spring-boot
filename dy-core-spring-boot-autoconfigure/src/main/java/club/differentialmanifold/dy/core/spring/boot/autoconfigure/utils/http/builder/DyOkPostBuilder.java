package club.differentialmanifold.dy.core.spring.boot.autoconfigure.utils.http.builder;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import club.differentialmanifold.dy.core.spring.boot.autoconfigure.utils.http.DyOkhttpUtils;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class DyOkPostBuilder {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private int type = 0;
    private MediaType mediaType;
    private static final int TYPE_PARAMS = 1;
    private static final int TYPE_JSON = 2;
    //json请求方式的mediaType
    private final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json;charset=utf-8");

    /**
     * 下面是解析参数，包括成功后 解析type
     */
    private String url;
    private String tag;
    private Map<String, String> headers;
    //post 键值对参数 (参数类型有很多，如键值对，string,byte,file,json等，这里主要封装2中json和键值对)
    private Map<String, String> params;
    //json 参数
    private String json;
    private boolean onlyOneNet;
    /**
     * okHttpUtils里单例里唯一
     */
    private OkHttpClient okHttpClient;

    /**
     * 每次请求网络生成的请求request
     */
    private Request okHttpRequest;

    public DyOkPostBuilder() {
        this.okHttpClient = DyOkhttpUtils.getInstance().getOkHttpClient();
    }


    public DyOkPostBuilder build() {
        Request.Builder mBuilder = new Request.Builder();
        validParams();
        mBuilder.url(url);

        if (StringUtils.hasLength(tag)) {
            mBuilder.tag(tag);
        }

        if (headers != null) {
            mBuilder.headers(appendHeaders(headers));
        }
        RequestBody requestBody = null;
        switch (type) {
            case TYPE_PARAMS:
                FormBody.Builder formBody = new FormBody.Builder();
                addParams(formBody, params);
                requestBody = formBody.build();
                break;
            case TYPE_JSON:
                requestBody = RequestBody.create(mediaType != null ? mediaType : MEDIA_TYPE_JSON, json);
                break;
        }
        //这里的.post是区分get请求的关键步骤
        assert requestBody != null;
        mBuilder.post(requestBody);

        okHttpRequest = mBuilder.build();
        return this;
    }

    public String execute() {
        try (Response response = okHttpClient.newCall(okHttpRequest).execute()) {
            if (response.isSuccessful()) {
                return Objects.requireNonNull(response.body()).string();
            }
        } catch (Exception e) {
            logger.error("okhttp3 post error >> ex = " + e);
        }

        return "";
    }


    public void removeOnceTag() {
        if (onlyOneNet) {
            if (StringUtils.hasLength(tag)) {
                DyOkhttpUtils.getInstance().getOnesTag().remove(tag);
            } else {
                DyOkhttpUtils.getInstance().getOnesTag().remove(url);
            }
        }
    }

    public DyOkPostBuilder onlyOneNet(boolean onlyOneNet) {
        this.onlyOneNet = onlyOneNet;
        return this;
    }

    public DyOkPostBuilder url(String url) {
        this.url = url;
        return this;
    }

    public DyOkPostBuilder tag(String tag) {
        this.tag = tag;
        return this;
    }


    public DyOkPostBuilder headers(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    public DyOkPostBuilder params(Map<String, String> params) {
        this.params = params;
        return this;
    }

    public DyOkPostBuilder json(String json) {
        this.json = json;
        return this;
    }


    //拼接头部参数
    public Headers appendHeaders(Map<String, String> headers) {
        Headers.Builder headerBuilder = new Headers.Builder();
        if (headers == null || headers.isEmpty()) return null;

        for (String key : headers.keySet()) {
            headerBuilder.add(key, headers.get(key));
        }
        return headerBuilder.build();
    }

    //键值对拼接的参数
    private void addParams(FormBody.Builder builder, Map<String, String> params) {
        if (builder == null) {
            throw new IllegalArgumentException("builder can not be null .");
        }

        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                builder.add(key, params.get(key));
            }
        }
    }


    //判断参数方式只能是一个
    protected void validParams() {
        int count = 0;
        if (params != null) {
            type = TYPE_PARAMS;
            count++;
        }

        if (json != null) {
            type = TYPE_JSON;
            count++;
        }

        if (count != 1) {
            throw new IllegalArgumentException("the params must has one and only one .");
        }
    }

}
