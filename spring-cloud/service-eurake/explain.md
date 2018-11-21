# spring-boot eureka

## service-eureka

该项目用于服务的注册，eureka的服务端

>主要依赖的包

注：使用工具创建项目

```
dependencies {
    compile('org.springframework.cloud:spring-cloud-starter-netflix-eureka-server:2.0.0.RELEASE')
    testCompile('org.springframework.boot:spring-boot-starter-test')
}

dependencyManagement {
    imports {
        mavenBom "org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}"
    }
}
``` 
