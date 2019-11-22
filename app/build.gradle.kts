import com.android.build.gradle.internal.dsl.BaseFlavor
import com.android.build.gradle.internal.dsl.DefaultConfig
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

plugins {
    id(GradlePluginId.ANDROID_APPLICATION)
    id(GradlePluginId.KOTLIN_ANDROID)
    id(GradlePluginId.KOTLIN_ANDROID_EXTENSIONS)
    id(GradlePluginId.FABRIC)
    id(GradlePluginId.KTLINT_GRADLE)
    id(GradlePluginId.SAFE_ARGS)
    id(GradlePluginId.KAPT)
}

android {
    compileSdkVersion(AndroidConfig.COMPILE_SDK_VERSION)

    defaultConfig {
        applicationId = AndroidConfig.ID
        minSdkVersion(AndroidConfig.MIN_SDK_VERSION)
        targetSdkVersion(AndroidConfig.TARGET_SDK_VERSION)
        buildToolsVersion(AndroidConfig.BUILD_TOOLS_VERSION)

        versionCode = AndroidConfig.VERSION_CODE
        versionName = AndroidConfig.VERSION_NAME
        testInstrumentationRunner = AndroidConfig.TEST_INSTRUMENTATION_RUNNER
    }

    buildTypes {
        getByName(BuildType.DEBUG) {
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            isDebuggable = true
            signingConfig = signingConfigs.getByName("debug")
            buildConfigField(BuildConfig.type, BuildConfig.baseUrl, BuildConfig.dev)
        }

        getByName(BuildType.RELEASE) {
            isDebuggable = false
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            proguardFiles("proguard-android.txt", "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("debug")
            buildConfigField(BuildConfig.type, BuildConfig.baseUrl, BuildConfig.prod)
        }

        testOptions {
            unitTests.isReturnDefaultValues = TestOptions.IS_RETURN_DEFAULT_VALUES
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
    }

    lintOptions {
        // By default lint does not check test sources, but setting this option means that lint will not even parse them
        isIgnoreTestSources = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        // "this" is currently lacking a proper type
        // See: https://youtrack.jetbrains.com/issue/KT-31077
        val options = this as? KotlinJvmOptions
        options?.jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

androidExtensions { isExperimental = true }

dependencies {

    api(LibraryDependency.KOTLIN)
    api(LibraryDependency.KOTLIN_REFLECT)

    api(LibraryDependency.KOIN)
    api(LibraryDependency.KOIN_VIEWMODEL)

    api(LibraryDependency.RETROFIT)
    api(LibraryDependency.RETROFIT_GSON_CONVERTER)
    implementation(LibraryDependency.LOGGING_INTERCEPTOR)
    implementation(LibraryDependency.STETHO)
    implementation(LibraryDependency.STETHO_OK_HTTP)

    api(LibraryDependency.APP_COMPACT)
    api(LibraryDependency.RECYCLER_VIEW)

    api(LibraryDependency.SUPPORT_CONSTRAINT_LAYOUT)
    api(LibraryDependency.MATERIAL)
    api(LibraryDependency.MATERIAL_DIALOG)
    api(LibraryDependency.COROUTINES_ANDROID)
    api(LibraryDependency.CORE_KTX)
    api(LibraryDependency.FRAGMENT_KTX)
    api(LibraryDependency.LIFECYCLE_EXTENSIONS)
    api(LibraryDependency.LIFECYCLE_VIEW_MODEL_KTX)
    api(LibraryDependency.ROOM_RUNTIME)
    api(LibraryDependency.ROOM_KTX)
    kapt(LibraryDependency.ROOM_COMPILER)

    api(LibraryDependency.TIMBER)
    implementation(LibraryDependency.CRASH_ANALYTICS)
    debugImplementation(LibraryDependency.LEAK_CANARY)

    addTestDependencies()
}

apply(mapOf("plugin" to GradlePluginId.GOOGLE_SERVICE))

fun BaseFlavor.buildConfigFieldFromGradleProperty(gradlePropertyName: String) {
    val propertyValue = project.properties[gradlePropertyName] as? String
    checkNotNull(propertyValue) { "Gradle property $gradlePropertyName is null" }

    val androidResourceName = "GRADLE_${gradlePropertyName.toSnakeCase()}".toUpperCase()
    buildConfigField("String", androidResourceName, propertyValue)
}

fun String.toSnakeCase() = this.split(Regex("(?=[A-Z])")).joinToString("_") { it.toLowerCase() }

fun DefaultConfig.buildConfigFields(type: String, name: String, value: String) {
    buildConfigField(type, name, value)
}
