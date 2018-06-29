
###s网关服务

---    
* 版本依赖
    >springCloud:Dalston.SR1     
    springBoot:1.5.2.RELEASE


#### Zuul 处理流程
Request => ZuulHandlerMapping => ZuulController => ZuulServlet

路由的逻辑处理主要是 ZuulServlet .service() .route() 即 Route Filter 进行的。

Route Filter
Zuul 中 Route Filter 有 SimpleHostRoutingFilter 和 RibbonRoutingFilter, 有人说还有 SendForwardFilter（本地的先不关注）

3.1 SimpleHostRoutingFilter

当你配置路由时，直接配置 Url 而不是 serviceId ，那么就是使用的 SimpleHostRoutingFilter，相反就是用的 RibbonRoutingFilter 。

Command


负载均衡策略:
    RandomRule：随机选择一个server
    BestAvailabl：选择一个最小的并发请求的server，逐个考察Server，如果Server被tripped了，则忽略
    RoundRobinRule：roundRobin方式轮询选择， 轮询index，选择index对应位置的server
    WeightedResponseTimeRule：根据响应时间分配一个weight(权重)，响应时间越长，weight越小，被选中的可能性越低
    RetryRule：对选定的负载均衡策略机上重试机制，在一个配置时间段内当选择server不成功，则一直尝试使用subRule的方式选择一个可用的server
    ZoneAvoidanceRule：复合判断server所在区域的性能和server的可用性选择server    
    ResponseTimeWeightedRule：作用同WeightedResponseTimeRule，二者作用是一样的，ResponseTimeWeightedRule后来改名为WeightedResponseTimeRule

IRule
这是所有负载均衡策略的父接口，里边的核心方法就是choose方法，用来选择一个服务实例

IRule<- AbstractLoadBalancerRule 
    <- ClientConfigEnabledRoundRobinRule // abstract
        <- BestAvailableRule // 最小连接优先轮询
            PredicateBasedRule // abstract
                 <- AvailabilityFilteringRule
                 <- ZoneAvoidanceRule
                  <- RoundRobinRule
                   <- WeightedResponseTimeRule
                    <- RandomRule
                     <- RetryRule



