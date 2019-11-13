import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.2.2.BUILD-SNAPSHOT"
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
    kotlin("jvm") version "1.3.50"
    kotlin("plugin.spring") version "1.3.50"
}

group = "com.chen.demo"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
    maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("org.springframework.boot:spring-boot-starter-test")

//    {
//        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
//    }


    implementation("com.spring4all:spring-boot-starter-hbase:1.0.0.RELEASE") {
        exclude(group = "org.apache.hbase", module = "hbase-client")
        exclude(group = "org.slf4j", module = "slf4j-api")
        exclude(group = "org.springframework.boot", module = "spring-boot-autoconfigure")
    }

    implementation("org.apache.hbase:hbase-client:2.2.2") {
        //        exclude(group = "log4j", module = "log4j")
        exclude(group = "org.slf4j", module = "slf4j-log4j12")
//        exclude(group = "javax.servlet", module = "servlet-api")
    }

    implementation("org.apache.hbase:hbase-server:2.2.2") {
        exclude(group = "org.slf4j", module = "slf4j-log4j12")

    }

//    implementation("org.springframework.boot:spring-boot-configuration-processor")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
