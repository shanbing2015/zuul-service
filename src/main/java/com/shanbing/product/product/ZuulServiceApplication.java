package com.shanbing.product.product;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


/**
 * Created by shanbing on 2018/3/24.
 */
@SpringBootApplication
@EnableZuulProxy    //声明zuul代理
//@EnableEurekaClient
public class ZuulServiceApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ZuulServiceApplication.class).web(true).run(args);
    }

}
