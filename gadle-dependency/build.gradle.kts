import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.1.5.RELEASE"
    id("io.spring.dependency-management") version "1.0.7.RELEASE"
    kotlin("jvm") version "1.3.31"
    kotlin("plugin.spring") version "1.3.31"
}

group = "com.chen.demo"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}


tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}


allprojects {
    tasks.register("hello") {
        doLast {
            println("I'm ${this.project.name}")
        }
    }
}

//subprojects {
//    val hello by tasks.existing
//
////    hello {
////        doLast { println("- I depend on kotlin-demo") }
////    }
//
//    afterEvaluate {
//        if (extra["arctic"] as Boolean) {
//            hello {
//                doLast {
//                    println("- this is gradle-dependency doLast.")
//                }
//            }
//        }
//    }
//}