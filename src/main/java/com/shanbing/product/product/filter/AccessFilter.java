package com.shanbing.product.product.filter;

import com.netflix.zuul.ZuulFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

public class AccessFilter extends ZuulFilter{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //pre：可以在请求被路由之前调用。
    //routing：在路由请求时候被调用。
    //post：在routing和error过滤器之后被调用。
    //error：处理请求时发生错误时被调用。
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    //通过int值来定义过滤器的执行顺序，数值越小优先级越高。
    @Override
    public int filterOrder() {
        return 0;
    }

    //是否应该执行该过滤器，如果是false，则不执行该filter
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        logger.debug("过滤器:");
        return null;
        /*RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String prefilter01 = request.getParameter("prefilter01");
        System.out.println("执行pre01Filter .....prefilter01=" + prefilter01  );

        //如果用户名和密码都正确，则继续执行下一个filter
        if("true".equals(prefilter01) ){
            ctx.setSendZuulResponse(true);//会进行路由，也就是会调用api服务提供者
            ctx.setResponseStatusCode(200);
            ctx.set("isOK",true);//可以把一些值放到ctx中，便于后面的filter获取使用
        }else{
            ctx.setSendZuulResponse(false);//不需要进行路由，也就是不会调用api服务提供者
            ctx.setResponseStatusCode(401);
            ctx.set("isOK",false);//可以把一些值放到ctx中，便于后面的filter获取使用
            //返回内容给客户端
            ctx.setResponseBody("{\"result\":\"pre01Filter auth not correct!\"}");// 返回错误内容
        }
        return null;*/
    }
}
