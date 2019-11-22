/**
 * Created by SangiliPandian C on 22-11-2019.
 */
@file:Suppress("detekt.StringLiteralDuplication")

private object LibraryVersion {
    const val KOIN = "2.0.1"
    const val RETROFIT = "2.6.2"
    const val LOGGING_INTERCEPTOR = "4.2.0"
    const val STETHO = "1.5.1"
    const val TIMBER = "4.7.1"
    const val PLAY_CORE = "1.6.4"
    const val APP_COMPACT = "1.1.0"
    const val RECYCLER_VIEW = "1.0.0"
    const val COORDINATOR_LAYOUT = "1.0.0"
    // 1.1.x version is required in order to support the dark theme functionality in
    // Android Q (adds Theme.MaterialComponents.DayNight)
    const val MATERIAL = "1.2.0-alpha01"
    const val MATERIAL_DIALOG = "3.1.1"
    const val CONSTRAINT_LAYOUT = "1.1.3"
    const val CORE_KTX = "1.1.0"
    const val FRAGMENT_KTX = "1.1.0"
    const val LIFECYCLE = "1.1.1"
    const val LIFECYCLE_VIEW_MODEL_KTX = "2.1.0"
    const val COIL = "0.7.0"
    const val LOTTIE = "3.0.7"
    const val ROOM = "2.2.1"
    const val WORK = "2.3.0-alpha03"
    const val VISION = "19.0.0"
    const val PERMISSION_DISPATCHER = "4.6.0"
    const val CRASH_ANALYTICS = "2.10.1"
    const val LEAK_CANARY = "2.0-beta-4"
}

object LibraryDependency {
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${CoreVersion.KOTLIN}"
    // Required by Android dynamic feature modules and SafeArgs
    const val KOTLIN_REFLECT = "org.jetbrains.kotlin:kotlin-reflect:${CoreVersion.KOTLIN}"
    const val KOIN = "org.koin:koin-android:${LibraryVersion.KOIN}"
    const val KOIN_VIEWMODEL = "org.koin:koin-androidx-viewmodel:${LibraryVersion.KOIN}"
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${LibraryVersion.RETROFIT}"
    const val RETROFIT_GSON_CONVERTER =
        "com.squareup.retrofit2:converter-gson:${LibraryVersion.RETROFIT}"
    const val LOGGING_INTERCEPTOR =
        "com.squareup.okhttp3:logging-interceptor:${LibraryVersion.LOGGING_INTERCEPTOR}"
    const val STETHO = "com.facebook.stetho:stetho:${LibraryVersion.STETHO}"
    const val STETHO_OK_HTTP = "com.facebook.stetho:stetho-okhttp3:${LibraryVersion.STETHO}"
    const val TIMBER = "com.jakewharton.timber:timber:${LibraryVersion.TIMBER}"
    const val SUPPORT_CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${LibraryVersion.CONSTRAINT_LAYOUT}"
    const val PLAY_CORE = "com.google.android.play:core:${LibraryVersion.PLAY_CORE}"
    const val APP_COMPACT = "androidx.appcompat:appcompat:${LibraryVersion.APP_COMPACT}"
    const val RECYCLER_VIEW = "androidx.recyclerview:recyclerview:${LibraryVersion.RECYCLER_VIEW}"
    const val COORDINATOR_LAYOUT =
        "androidx.coordinatorlayout:coordinatorlayout:${LibraryVersion.COORDINATOR_LAYOUT}"
    const val MATERIAL = "com.google.android.material:material:${LibraryVersion.MATERIAL}"
    const val COROUTINES_ANDROID =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${CoreVersion.COROUTINES_ANDROID}"
    const val CORE_KTX = "androidx.core:core-ktx:${LibraryVersion.CORE_KTX}"
    const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${LibraryVersion.FRAGMENT_KTX}"
    const val LIFECYCLE_EXTENSIONS = "android.arch.lifecycle:extensions:${LibraryVersion.LIFECYCLE}"
    const val LIFECYCLE_VIEW_MODEL_KTX =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${LibraryVersion.LIFECYCLE_VIEW_MODEL_KTX}"
    const val NAVIGATION_FRAGMENT_KTX =
        "androidx.navigation:navigation-fragment-ktx:${CoreVersion.NAVIGATION}"
    const val NAVIGATION_UI_KTX = "androidx.navigation:navigation-ui-ktx:${CoreVersion.NAVIGATION}"
    const val COIL = "io.coil-kt:coil:${LibraryVersion.COIL}"
    const val LOTTIE = "com.airbnb.android:lottie:${LibraryVersion.LOTTIE}"
    const val ROOM_RUNTIME = "androidx.room:room-runtime:${LibraryVersion.ROOM}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${LibraryVersion.ROOM}"
    const val ROOM_KTX = "androidx.room:room-ktx:${LibraryVersion.ROOM}"
    const val WORK_RUNTIME = "androidx.work:work-runtime-ktx:${LibraryVersion.WORK}"
    const val MATERIAL_DIALOG =
        "com.afollestad.material-dialogs:core:${LibraryVersion.MATERIAL_DIALOG}"
    const val VISION = "com.google.android.gms:play-services-vision:${LibraryVersion.VISION}"
    const val PERMISSION_DISPATCHER =
        "org.permissionsdispatcher:permissionsdispatcher:${LibraryVersion.PERMISSION_DISPATCHER}"
    const val PERMISSION_DISPATCHER_COMPILER =
        "org.permissionsdispatcher:permissionsdispatcher-processor:${LibraryVersion.PERMISSION_DISPATCHER}"
    const val CRASH_ANALYTICS =
        "com.crashlytics.sdk.android:crashlytics:${LibraryVersion.CRASH_ANALYTICS}"
    const val LEAK_CANARY =
        "com.squareup.leakcanary:leakcanary-android:${LibraryVersion.LEAK_CANARY}"
}
