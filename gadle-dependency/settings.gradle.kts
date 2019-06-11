pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}
rootProject.name = "gadle-dependency"

//import two child project
include("java-demo", "kotlin-demo")
