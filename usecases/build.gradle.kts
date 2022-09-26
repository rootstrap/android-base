plugins {
    with(Dependencies.Plugins) {
        id(ANDROID_LIB)
        kotlin(ANDROID)
    }
}

android {
    with (Dependencies.ConfigData) {
        compileSdk = COMPILE_SDK_VERSION

        defaultConfig {
            minSdk = MIN_SDK_VERSION
            targetSdk = TARGET_SDK_VERSION
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
        }

        create("staging") {
            initWith(getByName("debug"))
        }

        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    implementation(project(":data"))
    implementation(project(":domain"))

    with(Dependencies.Kotlin) {
        implementation(COROUTINES_ANDROID)
        implementation(KOTLIN_STDLIB)
        implementation(REFLECT)
    }

    with(Dependencies.Test) {
        testImplementation(CORE_TESTING)
        testImplementation(COROUTINES_TEST)
        testImplementation(JUNIT)
        testImplementation(MOCKITO_CORE)
        testImplementation(MOCKK)
    }
}
