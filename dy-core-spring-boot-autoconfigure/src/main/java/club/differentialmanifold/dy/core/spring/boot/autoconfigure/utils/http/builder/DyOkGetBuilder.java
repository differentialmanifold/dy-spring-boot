package club.differentialmanifold.dy.core.spring.boot.autoconfigure.utils.http.builder;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import club.differentialmanifold.dy.core.spring.boot.autoconfigure.utils.http.DyOkhttpUtils;
import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class DyOkGetBuilder {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 下面是解析参数，包括成功后 解析type
     */
    private String url;
    private String tag;
    private Map<String, String> headers;
    private Map<String, String> params;
    private boolean onlyOneNet;
    /**
     * okHttpUtils里单例里唯一
     */
    private OkHttpClient okHttpClient;

    /**
     * 每次请求网络生成的请求request
     */
    private Request okHttpRequest;

    public DyOkGetBuilder() {
        this.okHttpClient = DyOkhttpUtils.getInstance().getOkHttpClient();
    }

    public DyOkGetBuilder build() {
        Request.Builder mBuilder = new Request.Builder();
        if (params != null) {
            mBuilder.url(appendParams(url, params));
        } else {
            mBuilder.url(url);
        }

        if (StringUtils.hasLength(tag)) {
            mBuilder.tag(tag);
        }

        if (headers != null) {
            mBuilder.headers(Objects.requireNonNull(appendHeaders(headers)));
        }

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

    public DyOkGetBuilder url(String url) {
        this.url = url;
        return this;
    }

    public DyOkGetBuilder onlyOneNet(boolean onlyOneNet) {
        this.onlyOneNet = onlyOneNet;
        return this;
    }

    public DyOkGetBuilder tag(String tag) {
        this.tag = tag;
        return this;
    }


    public DyOkGetBuilder headers(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    public DyOkGetBuilder params(Map<String, String> params) {
        this.params = params;
        return this;
    }


    private Headers appendHeaders(Map<String, String> headers) {
        Headers.Builder headerBuilder = new Headers.Builder();
        if (headers == null || headers.isEmpty()) return null;

        for (String key : headers.keySet()) {
            headerBuilder.add(key, headers.get(key));
        }
        return headerBuilder.build();
    }


    //get 参数拼在url后面
    private String appendParams(String url, Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        if (!url.contains("?")) {
            sb.append(url).append("?");
        } else {
            sb.append(url).append("&");
        }

        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                sb.append(key).append("=").append(params.get(key)).append("&");
            }
        }

        sb = sb.deleteCharAt(sb.length() - 1);
        logger.info("请求接口 ==>> " + sb.toString());
        return sb.toString();
    }
}
