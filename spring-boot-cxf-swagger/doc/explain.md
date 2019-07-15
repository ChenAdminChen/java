+ [x] 支持cxf的swagger文档

> 依赖
```
   <dependency>
        <groupId>org.webjars</groupId>
        <artifactId>swagger-ui</artifactId>
        <version>3.22.2</version>
   </dependency>

   <dependency>
        <groupId>org.apache.cxf</groupId>
        <artifactId>cxf-rt-rs-service-description-swagger</artifactId>
        <version>3.3.2</version>
   </dependency>
```

> 配置文件修改

```
cxf:
  jaxrs:
    component-scan: true
    #    classes-scan: true
    classes-scan-packages: org.apache.cxf.jaxrs.swagger,org.apache.cxf.metrics
  #    classes-scan-packages: org.apache.cxf.jaxrs.swagger #com.yf.af.biz.service,com.fasterxml.jackson.jaxrs.json
  path: /
```

> 在resources中添加swagger.properties文件

> 添加访问swagger权限控制

修改SecurityConfig.java
```
 @Override
    public void configure(WebSecurity web) throws Exception {

    web.ignoring().antMatchers(
            "/api-docs",
            "/api-docs/**",
            "/swagger.json"
    );
}
```

> 访问地址

```http request
http://localhost:8601/api-docs/?uri=/swagger.json

# 进入页面后输入需要访问的swagger.json地址

/swagger.json

```
