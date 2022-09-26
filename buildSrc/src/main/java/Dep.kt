object Dependencies {
    object Android {
        const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"
        const val CORE = "androidx.core:core-ktx:${Versions.CORE}"
        const val CONSTRAINT_LAYOUT =
            "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
        const val LEGACY_SUPPORT_V4 = "androidx.legacy:legacy-support-v4:${Versions.LEGACY_SUPPORT_V4}"
        const val PREFERENCE = "androidx.preference:preference-ktx:${Versions.PREFERENCE}"
        const val RECYCLERVIEW = "androidx.recyclerview:recyclerview:${Versions.RECYCLERVIEW}"
        const val WORK_RUNTIME = "androidx.work:work-runtime-ktx:${Versions.WORK_RUNTIME}"

        // Lifecycle
        const val LIFECYCLE_COMMON = "android.arch.lifecycle:common-java8:${Versions.LIFECYCLE_COMMON}"
        const val LIFECYCLE_RUNTIME = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE}"
        const val LIFECYCLE_LIVEDATA = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFECYCLE}"
        const val LIFECYCLE_EXTENSIONS =
            "androidx.lifecycle:lifecycle-extensions:${Versions.LIFECYCLE_EXTENSION}"
        const val LIFECYCLE_VIEW_MODEL =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE}"

        // Navigation
        const val NAVIGATION_FRAGMENT =
            "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"
        const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"

        // Room
        const val ROOM_RUNTIME = "androidx.room:room-runtime:${Versions.ROOM}"

    }

    object Common {
        // Image
        const val GLIDE = "com.github.bumptech.glide:glide:${Versions.GLIDE}"
        // MixPanel
        const val MIX_PANEL = "com.mixpanel.android:mixpanel-android:${Versions.MIX_PANEL}"
        // Events
        const val OTTO = "com.squareup:otto:${Versions.OTTO}"
        // Security crypto
        const val SECURITY_CRYPTO = "androidx.security:security-crypto:${Versions.SECURITY_CRYPTO}"
    }

    object Google {
        const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL_DESIGN}"
        const val GSON = "com.google.code.gson:gson:${Versions.GSON}"
        const val FIREBASE_BOM = "com.google.firebase:firebase-bom:${Versions.FIREBASE_BOM}"
        const val FIREBASE_CORE = "com.google.firebase:firebase-core"
        const val FIREBASE_ANALYTICS = "com.google.firebase:firebase-analytics-ktx"
        const val FIREBASE_CRASHLYTICS = "com.google.firebase:firebase-crashlytics-ktx"
    }

    object Kapt {
        const val GLIDE_KAPT = "com.github.bumptech.glide:compiler:${Versions.GLIDE}"
        const val LIFECYCLE_KAPT = "android.arch.lifecycle:compiler:${Versions.LIFECYCLE_COMMON}"
        const val ROOM_KAPT = "androidx.room:room-compiler:${Versions.ROOM}"
    }

    object Koin {
        const val CORE = "io.insert-koin:koin-core:${Versions.KOIN}"
        const val ANDROID = "io.insert-koin:koin-android:${Versions.KOIN}"
        const val ANDROID_NAVIGATION = "io.insert-koin:koin-androidx-navigation:${Versions.KOIN}"
        const val ANDROID_COMPAT = "io.insert-koin:koin-android-compat:${Versions.KOIN}"
        const val WORK_MANAGER = "io.insert-koin:koin-androidx-workmanager:${Versions.KOIN}"
        const val COMPOSE = "io.insert-koin:koin-androidx-compose:${Versions.KOIN}"
    }

    object Kotlin {
        const val KOTLIN_STDLIB = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.KOTLIN}"
        const val COROUTINES_ANDROID =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES}"
        const val REFLECT = "org.jetbrains.kotlin:kotlin-reflect:${Versions.KOTLIN}"
    }

    object Network {
        const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
        const val RETROFIT_GSON_CONVERTER =
            "com.squareup.retrofit2:converter-gson:${Versions.CONVERTER_GSON}"
        const val MOSHI = "com.squareup.moshi:moshi-kotlin:${Versions.MOSHI}"
        const val CONVERTER_MOSHI = "com.squareup.retrofit2:converter-moshi:${Versions.CONVERTER_MOSHI}"
        const val LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP}"
        const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP}"
    }

    // Linters
    const val KT_LINT = "com.pinterest:ktlint:${Versions.KT_LINT}"

    object Test {
        const val JUNIT = "junit:junit:${Versions.JUNIT}"
        const val MOCKITO_CORE = "org.mockito:mockito-core:${Versions.MOCKITO_CORE}"
        const val MOCKK = "io.mockk:mockk:${Versions.MOCKK}"
        const val COROUTINES_TEST =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.COROUTINES}"
        const val CORE_TESTING = "android.arch.core:core-testing:${Versions.CORE_TESTING}"
    }

    object AndroidTest {
        const val MOCKK_ANDROID = "io.mockk:mockk-android:${Versions.MOCKK}"
        const val TEST_RUNNER = "androidx.test:runner:${Versions.TEST_RUNNER}"
        const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO}"
        const val ESPRESSO_INTENTS = "androidx.test.espresso:espresso-intents:${Versions.ESPRESSO}"
        const val EXT_JUNIT = "androidx.test.ext:junit:${Versions.EXT_JUNIT}"
        const val KOIN_TEST = "io.insert-koin:koin-test:${Versions.KOIN}"
        const val UI_AUTOMATOR = "androidx.test.uiautomator:uiautomator:${Versions.UI_AUTOMATOR}"
        const val RULES = "androidx.test:rules:${Versions.RULES}"
        const val MOCK_WEBSERVER = "com.squareup.okhttp3:mockwebserver:${Versions.MOCK_WEBSERVER}"
    }

    object ClassPaths {
        const val ANDROID_GRADLE_PLUGIN = "com.android.tools.build:gradle:${Versions.ANDROID_GRADLE_PLUGIN}"
        const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"
        const val GMS = "com.google.gms:google-services:${Versions.GOOGLE_SERVICES}"
        const val FIREBASE_CRASHLYTICS_GRADLE = "com.google.firebase:firebase-crashlytics-gradle:${Versions.FIREBASE_CRASHLYTICS_GRADLE}"
    }

    object Versions {
        const val ANDROID_GRADLE_PLUGIN = "7.1.2"
        const val APP_COMPAT = "1.3.1"
        const val CONSTRAINT_LAYOUT = "2.1.1"
        const val CONVERTER_GSON = "2.9.0"
        const val CONVERTER_MOSHI = "2.5.0"
        const val CORE = "1.6.0"
        const val COROUTINES = "1.5.1"
        const val CORE_TESTING = "1.1.1"
        const val ESPRESSO = "3.4.0"
        const val EXT_JUNIT = "1.1.3"
        const val FIREBASE_BOM = "26.1.0"
        const val FIREBASE_CRASHLYTICS_GRADLE = "2.8.1"
        const val GLIDE = "4.12.0"
        const val GOOGLE_SERVICES = "4.3.10"
        const val GSON = "2.8.7"
        const val JUNIT = "4.13.2"
        const val KOIN = "3.1.3"
        const val KOTLIN = "1.6.10"
        const val KT_LINT = "0.44.0"
        const val LEGACY_SUPPORT_V4 = "1.0.0"
        const val LIFECYCLE = "2.4.0"
        const val LIFECYCLE_COMMON = "1.1.1"
        const val LIFECYCLE_EXTENSION = "2.2.0"
        const val MATERIAL_DESIGN = "1.4.0"
        const val MIX_PANEL = "5.6.1"
        const val MOCKK = "1.12.0"
        const val MOCK_WEBSERVER = "4.9.0"
        const val MOCKITO_CORE = "2.28.2"
        const val MOSHI = "1.12.0"
        const val NAVIGATION = "2.3.5"
        const val OKHTTP = "4.9.0"
        const val OTTO = "1.3.8"
        const val PREFERENCE = "1.1.1"
        const val RECYCLERVIEW = "1.1.0"
        const val RETROFIT = "2.9.0"
        const val ROOM = "2.4.1"
        const val RULES = "1.4.0"
        const val SECURITY_CRYPTO = "1.1.0-alpha03"
        const val TEST_RUNNER = "1.4.0"
        const val UI_AUTOMATOR = "2.2.0"
        const val WORK_RUNTIME  = "2.7.0"
    }

    object ConfigData {
        const val COMPILE_SDK_VERSION = 32
        const val BUILD_TOOLS_VERSION = "31.0.0"
        const val MIN_SDK_VERSION = 23
        const val TARGET_SDK_VERSION = 32
        const val VERSION_CODE = 43
        const val VERSION_NAME = "1.0"
    }

    object Plugins {
        const val ANDROID = "android"
        const val ANDROID_APP = "com.android.application"
        const val ANDROID_LIB = "com.android.library"
        const val CRASHLYTICS = "com.google.firebase.crashlytics"
        const val GOOGLE_SERVICES = "com.google.gms.google-services"
        const val KAPT = "kapt"
    }
}


