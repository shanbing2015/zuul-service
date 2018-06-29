package top.shanbing.Provider;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * 自定义路由服务失败 fallback响应
 */
public class FallbackClientHttpResponse implements ClientHttpResponse{
    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.OK;
    }

    @Override
    public int getRawStatusCode() {
        return this.getStatusCode().value();
    }

    @Override
    public String getStatusText() {
        return this.getStatusCode().getReasonPhrase();
    }

    @Override
    public void close() {

    }

    @Override
    public InputStream getBody() {
        return new ByteArrayInputStream("服务暂不可用".getBytes());
    }

    @Override
    public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        MediaType mt = new MediaType("application", "json", Charset.forName("UTF-8"));
        headers.setContentType(mt);
        return headers;
    }
}
