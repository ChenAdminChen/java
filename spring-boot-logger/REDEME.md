# spring boot logger(log4j2)

[学习地址](https://docs.spring.io/spring-boot/docs/current/reference/html/howto-logging.html)

> run environment

 kotlin + gradle/maven + spring-boot


> add dependency
```gradle

    implementation("org.springframework.boot:spring-boot-starter-log4j2")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.9.9")
        
    //开启异步日志输出
    // java -jar xxx.jar -DLog4jContextSelector=org.apache.logging.log4j.core.async.AsyncLoggerContextSelector  
    //implementation("com.lmax:disruptor:3.4.2")

//排除掉spring-boot 自带的logging
configurations {
    all {
        exclude("org.springframework.boot", "spring-boot-starter-logging")
    }
}
```

```maven

<!-- Exclude Spring Boot's Default Logging 如果把logging移除了，idea打印的貌似就不是彩色的了。 -->
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter</artifactId>
	<exclusions>
		<exclusion>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-logging</artifactId>
		</exclusion>
	</exclusions>
</dependency>

<!-- Add Log4j2 Dependency -->
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-log4j2</artifactId>
</dependency>

<dependency>
	<groupId>com.lmax</groupId>
	<artifactId>disruptor</artifactId>
	<version>3.4.2</version>
</dependency>

```

> add log4j2.yml

```yml
Configuration:
  status: debug

  appenders:
    # 控制台输出日志
    Console:
      name: LogToConsole
      PatternLayout:
        Pattern: "[%-5level] %d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %c{1} - %msg%n"
    # 将日志输出文件
    RollingFile:
      - name: LogToRollingFile
        fileName: logs/app.log
        filePattern: "logs/$${date:yyyy-MM}/app-%d{MM-dd-yyyy}-%i.log.gz"
        PatternLayout:
          pattern: "[%-5level] %d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %c{1} - %msg%n"
        Policies:
          SizeBasedTriggeringPolicy:
            size: 10MB
        DefaultRollOverStrategy:
          max: 10
  
  Loggers:
    logger:
    //项目中某个包错误日志输出
      - name: com.chen.demo
        level: debug
        additivity: false
        AppenderRef:
#          - ref: LogToConsole
          - ref: LogToRollingFile
    
    //项目中错误日志输出
    Root:
      level: error
      AppenderRef:
        - ref: LogToConsole
        - ref: LogToRollingFile
```

> use logger

```kotlin

package com.chen.demo.controller

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.RuntimeException

@RestController
class TestController {

    val log: Logger = LogManager.getLogger(TestController::class.java)

    @GetMapping("/hello")
    fun getHello(): ResponseEntity<String> {

        log.debug( RuntimeException("successs-----------------------").toString())
        return throw RuntimeException("successs-----------------------")
//        return ResponseEntity.ok("success");

    }
}

```

```java
//若项目中带有lombok时可使用注解 @Slft4
@Slft4
public class TestController{
    @GetMapping("/hello")
        public ResponseEntity<String> getHello() {
    
            log.debug(new RuntimeException("successs-----------------------").toString());
            throw new RuntimeException("successs-----------------------");
    //        return ResponseEntity.ok("success");
    
        }
}

//无lombok
public class TestController{
    private static Logger log = LogManager.getLogger(TestController.java);

    @GetMapping("/hello")
        public ResponseEntity<String> getHello() {
    
            log.debug(new RuntimeException("successs-----------------------").toString());
            throw new RuntimeException("successs-----------------------");
    //        return ResponseEntity.ok("success");
    
        }
}
```
