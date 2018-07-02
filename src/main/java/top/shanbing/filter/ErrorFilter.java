package top.shanbing.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ErrorFilter  extends ZuulFilter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String filterType() {
        return FilterConstants.ERROR_TYPE;
    }

    @Override
    public int filterOrder() {
        logger.info("get filterOrder");
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        logger.info("FilterConstants ERROR");
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.setSendZuulResponse(false);//不需要进行路由，也就是不会调用api服务提供者
        ctx.setResponseStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        ctx.set("isOK",false);//可以把一些值放到ctx中，便于后面的filter获取使用
        //返回内容给客户端
        ctx.setResponseBody("{\"result\":\"服务器异常\"}");// 返回错误内容
        return null;
    }
}
