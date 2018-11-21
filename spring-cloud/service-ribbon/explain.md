## service-ribbon

用于处理并发操作，实现负载均衡，并且它存在断熔处理

eureka service + eureka client + zuul (feign -> ribbon)

> 依赖的包

```

dependencies {
    compile('org.springframework.boot:spring-boot-starter')

    compile('org.springframework.boot:spring-boot-starter-web')
    compile('org.springframework.cloud:spring-cloud-starter-netflix-ribbon:1.4.5.RELEASE')
    compile('org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:2.0.0.RELEASE')

    compile('org.springframework.cloud:spring-cloud-starter-netflix-hystrix:2.0.1.RELEASE')

    testCompile('org.springframework.boot:spring-boot-starter-test')


}
```