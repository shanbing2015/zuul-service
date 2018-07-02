package top.shanbing.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RateLimitZuulFilter extends ZuulFilter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RateLimiter rateLimiter = RateLimiter.create(100);

    //@Value("product.rateLimit.shouldFilter")
    private boolean shouldFilter = true;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    @Override
    public boolean shouldFilter() {
        return shouldFilter;
    }

    @Override
    public Object run() {
        try {
            RequestContext currentContext = RequestContext.getCurrentContext();
            HttpServletResponse response = currentContext.getResponse();

            //logger.info("获取令牌所需时间:"+rateLimiter.acquire());
            //logger.info("获取100个令牌所需时间:"+rateLimiter.acquire(100));

            if (!rateLimiter.tryAcquire()) {
                HttpStatus httpStatus = HttpStatus.TOO_MANY_REQUESTS;
                currentContext.setResponseStatusCode(httpStatus.value());
                currentContext.setSendZuulResponse(false);//无需路由

                response.setStatus(httpStatus.value());
                response.setContentType(MediaType.TEXT_PLAIN_VALUE);
                response.getWriter().append("请求接口过多，请稍后再试");
                //currentContext.setResponseBody("{\"result\":\"请求接口过多，请稍后再试\"}");
                currentContext.setResponse(response);
            }
        }catch (IOException ioe){
            logger.error("限流过滤器响应异常");
        }
        return null;
    }
}
