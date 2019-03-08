# introduction

## jpa的复合主键关联查询

## spring-boot-data-rest使用

## http://127.0.0.1:8080访问

## 多配置文件存在时指定某个文件使用

### 多文件

>(application.yml ,application-dev.yml,application-por.yml)

```
#application.yml内设定

spring:
  profiles:
    active: dev
```

### 多文件

>(application-dev.yml,application-por.yml)

```
-Dspring.profiles.active=dev
```

## 运行jar时指定参数

```
--spring.profiles.active=dev
```

##  多文档块用法

>application.yml  
    
在application.yml中存在三个文件

```
spring:
  profiles:
    active: dev

---
spring:
  profiles: dev
server:
  port: 8082
---
spring:
  profiles: por
server:
  port: 8083

```

## 配置文件加载的顺序

（在项目内用工具运行 顺序如下）
优先级由高到底，高优先级的配置会覆盖低优先级的配置
SpringBoot会从这个四个位置全部加载，互补配置
```
-file:./config/  --项目文件的config文件下
-file:./   --项目文件内
-classpath:/config/  --resources的cofig文件下
-classpath:/  --resources文件内
```

（项目打包运行时）
```
-classpath:/config/  --resources的cofig文件下
-classpath:/  --resources文件内
```