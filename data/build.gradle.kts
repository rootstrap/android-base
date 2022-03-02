plugins {
    with(Dependencies.Plugins) {
        id(ANDROID_LIB)
        kotlin(ANDROID)
        id("kotlin-parcelize")
    }
}

android {
    with(Dependencies.ConfigData) {
        compileSdk = COMPILE_SDK_VERSION

        defaultConfig {
            minSdk = MIN_SDK_VERSION
            targetSdk = TARGET_SDK_VERSION
            testInstrumentationRunner = "com.rootstrap.android.CustomTestRunner"
        }
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            buildConfigField("String", "API_URL", "\"https://rails5-api-base.herokuapp.com/api/v1/\"")
            buildConfigField("String", "MIX_PANEL_KEY", "\"mixpanel_api_key\"")
        }

        create("staging") {
            isMinifyEnabled = false
            buildConfigField("String", "API_URL", "\"https://proj-staging.herokuapp.com/api/\"")
            buildConfigField("String", "MIX_PANEL_KEY", "\"mixpanel_api_key\"")
        }

        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            buildConfigField("String", "API_URL", "\"https://proj-production.herokuapp.com/api/\"")
            buildConfigField("String", "MIX_PANEL_KEY", "\"mixpanel_api_key\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":domain"))

    with(Dependencies.Kotlin) {
        implementation(COROUTINES_ANDROID)
        implementation(KOTLIN_STDLIB)
        implementation(REFLECT)
    }

    implementation(Dependencies.Android.PREFERENCE)
    implementation(Dependencies.Common.MIX_PANEL)
    implementation(platform(Dependencies.Google.FIREBASE_BOM))

    with(Dependencies.Google) {
        implementation(MATERIAL)
        implementation(FIREBASE_CORE)
        implementation(FIREBASE_ANALYTICS)
        implementation(FIREBASE_CRASHLYTICS)
        implementation(GSON)
    }

    with(Dependencies.Network) {
        implementation(CONVERTER_MOSHI)
        implementation(LOGGING_INTERCEPTOR)
        implementation(MOSHI)
        implementation(OKHTTP)
        implementation(RETROFIT)
        implementation(RETROFIT_GSON_CONVERTER)
    }

    with(Dependencies.Test) {
        testImplementation(CORE_TESTING)
        testImplementation(COROUTINES_TEST)
        testImplementation(JUNIT)
        testImplementation(MOCKITO_CORE)
        testImplementation(MOCKK)
    }
}
