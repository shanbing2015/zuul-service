package top.shanbing.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 路由失败处理
 */
@Component
public class ServiceConsumerFallbackProvider implements ZuulFallbackProvider{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostConstruct
    public void init(){
        logger.info("路由失败处理初始化-ServiceConsumerFallbackProvider init");
    }

    @Override
    public String getRoute() {
        logger.info("获取路由服务");
        //return "mail-service";  //api服务id，如果需要所有调用都支持回退，则return "*"或return null
        return null;
    }

    @Override
    public ClientHttpResponse fallbackResponse() {
        logger.info("Hystrix断路器生效");
        return new FallbackClientHttpResponse();
    }
}
