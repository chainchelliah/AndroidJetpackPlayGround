pluginManagement {
    repositories {
        gradlePluginPortal()
        maven(url = "https://maven.fabric.io/public")
        maven(url = "https://jitpack.io")
        google()
    }

    plugins {
        id(GradlePluginId.DETEKT) version GradlePluginVersion.DETEKT
        id(GradlePluginId.KTLINT_GRADLE) version GradlePluginVersion.KTLINT_GRADLE
        id(GradlePluginId.GRADLE_VERSION_PLUGIN) version GradlePluginVersion.GRADLE_VERSION_PLUGIN
        id(GradlePluginId.KOTLIN_JVM) version GradlePluginVersion.KOTLIN
        id(GradlePluginId.KOTLIN_ANDROID) version GradlePluginVersion.KOTLIN
        id(GradlePluginId.KOTLIN_ANDROID_EXTENSIONS) version GradlePluginVersion.KOTLIN
        id(GradlePluginId.ANDROID_APPLICATION) version GradlePluginVersion.ANDROID_GRADLE
        id(GradlePluginId.ANDROID_LIBRARY) version GradlePluginVersion.ANDROID_GRADLE
        id(GradlePluginId.ANDROID_DYNAMIC_FEATURE) version GradlePluginVersion.ANDROID_GRADLE
        id(GradlePluginId.SAFE_ARGS) version GradlePluginVersion.SAFE_ARGS
        id(GradlePluginId.GOOGLE_SERVICE) version GradlePluginVersion.GOOGLE_SERVICE
        id(GradlePluginId.FABRIC) version GradlePluginVersion.FABRIC
    }

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                GradlePluginId.ANDROID_APPLICATION,
                GradlePluginId.ANDROID_LIBRARY,
                GradlePluginId.GOOGLE_SERVICE -> useModule(GradleOldWayPlugins.GOOGLE_SERVICE)
                GradlePluginId.FABRIC -> useModule(GradleOldWayPlugins.FABRIC)
                GradlePluginId.ANDROID_DYNAMIC_FEATURE -> useModule(GradleOldWayPlugins.ANDROID_GRADLE)
                GradlePluginId.SAFE_ARGS -> useModule(GradleOldWayPlugins.SAFE_ARGS)
            }
        }
    }
}

rootProject.buildFileName = "build.gradle.kts"
include(
    ModuleDependency.APP
)
