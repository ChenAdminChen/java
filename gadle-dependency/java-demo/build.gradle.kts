plugins {
    id("java")
}


group = "com.chen.demo"

version = "0.0.1-SNAPSHOT"

// sourceCompatibility = 1.8

java.sourceCompatibility = JavaVersion.VERSION_1_8
repositories {
    mavenCentral()
}

dependencies {
    testImplementation("junit:junit:4.12")

    implementation("org.projectlombok:lombok:1.18.8")
    annotationProcessor("org.projectlombok:lombok:1.18.8")

}

//extra["arctic"] = true
//tasks.named("hello") {
//    doLast {
//        println("- I'm the java-demo")
//    }
//}