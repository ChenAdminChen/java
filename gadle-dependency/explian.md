# gradle mutual dependency


## gadle-dependency

1. use gradle version is 5.4.1
2. gadle-dependency is java-demo/kotlin-demo/app rootProject

> add child gradle project

add configuration in the settings.gradle.kts

```kotlin
include("app", "java-demo", "kotlin-demo")

```
## java-demo

1. use java language
2. use gradle version is 5.4.1
3. use lombok annotation

## kotlin-demo

1. use kotlin language
2. use gradle version is 5.4.1
3. dependency java-demo
4. use spring-boot and dependency

> kotlin-demo in intellij idea test

solve lombok annotation not effect in idea tools 
```
setting -> Build Execution Deployment -> compiler -> annotation processor -> Enable annotation processing =true

```

> spring-boot project provider jar 

solve bootRun and jar meanwhile, at build.gradle.kts add configuration

```kotlin
//jar is true
tasks.getByName<Jar>("jar") {
    enabled = true
}

```

## app

1. use java language
2. use gradle version is 5.4.1
3. dependency java-demo,kotlin-demo
4. use dependency spring-boot and dependency

> current spring-boot use other spring-boot project class

other spring-boot must not exit @SpringBootApplication annotation

> current project dependency other child gradle project

add configuration in the build.gradle.kts

```kotlin

    implementation(project(":java-demo"))
    implementation(project(":kotlin-demo"))

```


