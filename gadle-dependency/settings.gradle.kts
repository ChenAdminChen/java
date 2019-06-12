pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}
rootProject.name = "gadle-dependency"

//import two child project
include("app", "java-demo", "kotlin-demo")
