## spring-boot-swagger introduce

### add dependence

```groovy
    compile 'io.swagger.core.v3:swagger-annotations:2.0.8'
    
    compile group: 'io.springfox', name: 'springfox-swagger2', version: '2.9.2'
    compile group: 'io.springfox', name: 'springfox-swagger-ui', version: '2.9.2'
    compile group: 'io.springfox', name: 'springfox-staticdocs', version: '2.6.1'
    
    testCompile group: 'org.springframework.restdocs', name: 'spring-restdocs-mockmvc', version: '2.0.2.RELEASE'
``` 

### configuration spring-boot-swagger

> in the SwaggerConfig.kt

### web request url

> url:  

http://localhost:8080/swagger-ui.html#/

### create swagger.json

> in the MavenGenApiDoc.java

### carate paths.md/definition.md...

>in the GenApiDoc.java

