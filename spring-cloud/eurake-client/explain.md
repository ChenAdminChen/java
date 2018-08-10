## eureka-client

该项目为普通的restful接口服务，但引用了eureka client，将自己注入到eureka service中去，可在eureka service的网页中发现该服务

>依赖

```
dependencies {
    compile('org.springframework.cloud:spring-cloud-starter-netflix-eureka-client')
    testCompile('org.springframework.boot:spring-boot-starter-test')
    compile('org.springframework.boot:spring-boot-starter-web')
}

dependencyManagement {
    imports {
        mavenBom "org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}"
    }
}
```

> 注入到eureka service中

```
eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/  --为eureka service的注册地址，可到service-eurake项目中查看
```

> 运行多个实例
在ide 的 edit configuration 的run/debug configuration 中的去掉 single _instance only选项