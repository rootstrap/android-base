object Versions {
    const val appCompat = "1.3.1"
    const val constraintLayout = "2.1.1"
    const val core = "1.6.0"
    const val coroutines = "1.5.1"
    const val legacySupportV4 = "1.0.0"
    const val preference = "1.1.1"
    const val recyclerview = "1.1.0"
    const val gson = "2.8.7"
    const val firebaseBom = "26.1.0"
    const val navigation = "2.3.5"
    const val glide = "4.12.0"
    const val kotlin = "1.6.0"
    const val koin = "3.1.2"
    const val lifecycle = "2.4.0"
    const val lifecycleCommon = "1.1.1"
    const val lifecycleExtension = "2.2.0"
    const val materialDesign = "1.4.0"
    const val moshi = "1.12.0"
    const val converterMoshi = "2.5.0"
    const val converterGson = "2.9.0"
    const val okhttp = "4.9.0"
    const val otto = "1.3.8"
    const val ktlint = "0.35.0"
    const val securityCrypto = "1.1.0-alpha03"
    const val retrofit = "2.9.0"
    const val room = "1.1.1"
    const val junit = "4.13.2"
    const val mockitoCore = "2.28.2"
    const val mockk = "1.12.0"
    const val espresso = "3.4.0"
    const val extJunit = "1.1.3"
    const val uiautomator = "2.2.0"
    const val rules = "1.4.0"
    const val mockwebserver = "4.9.0"
    const val testRunner = "1.4.0"
    const val mixPanel = "5.6.1"
    const val coreTesting = "1.1.1"
}

object Libs {
    // Kotlin
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"

    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val constraintlayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val legacySupportV4 = "androidx.legacy:legacy-support-v4:${Versions.legacySupportV4}"
    const val preference = "androidx.preference:preference-ktx:${Versions.preference}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"

    // Material
    const val material = "com.google.android.material:material:${Versions.materialDesign}"

    // Room
    const val roomRuntime = "android.arch.persistence.room:runtime:${Versions.room}"
    const val roomKapt = "android.arch.persistence.room:compiler:${Versions.room}"

    // Lifecycle
    const val lifecycleCommon = "android.arch.lifecycle:common-java8:${Versions.lifecycleCommon}"
    const val lifecycleKapt = "android.arch.lifecycle:compiler:${Versions.lifecycleCommon}"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val lifecycleLivedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val lifecycleExtensions =
        "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleExtension}"
    const val lifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"

    // Navigation
    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    // Google JSON serializer/deserializer
    const val gson = "com.google.code.gson:gson:${Versions.gson}"

    // Firebase
    const val firebaseBom = "com.google.firebase:firebase-bom:${Versions.firebaseBom}"
    const val firebaseCore = "com.google.firebase:firebase-core"
    const val firebaseAnalytics = "com.google.firebase:firebase-analytics-ktx"
    const val firebaseCrashlytics = "com.google.firebase:firebase-crashlytics-ktx"

    //---- MixPanel ----
    const val mixPanel = "com.mixpanel.android:mixpanel-android:${Versions.mixPanel}"

    // Image
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideKapt = "com.github.bumptech.glide:compiler:${Versions.glide}"

    // Network
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGsonConverter =
        "com.squareup.retrofit2:converter-gson:${Versions.converterGson}"
    const val moshi = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    const val converterMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.converterMoshi}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"

    // Events
    const val otto = "com.squareup:otto:${Versions.otto}"

    // Linters
    const val ktlint = "com.pinterest:ktlint:${Versions.ktlint}"

    // Koin
    const val koinCore = "io.insert-koin:koin-core:${Versions.koin}"
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"
    const val koinAndroidNavigation = "io.insert-koin:koin-androidx-navigation:${Versions.koin}"
    const val koinAndroidCompat = "io.insert-koin:koin-android-compat:${Versions.koin}"
    const val koinWorkManager = "io.insert-koin:koin-androidx-workmanager:${Versions.koin}"
    const val koinCompose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"

    // Security crypto
    const val securityCrypto = "androidx.security:security-crypto:${Versions.securityCrypto}"

    // Testing
    const val junit = "junit:junit:${Versions.junit}"
    const val mockitoCore = "org.mockito:mockito-core:${Versions.mockitoCore}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val mockkAndroid = "io.mockk:mockk-android:${Versions.mockk}"
    const val testRunner = "androidx.test:runner:${Versions.testRunner}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val espressoIntents = "androidx.test.espresso:espresso-intents:${Versions.espresso}"
    const val extJunit = "androidx.test.ext:junit:${Versions.extJunit}"
    const val koinTest = "io.insert-koin:koin-test:${Versions.koin}"
    const val uiautomator = "androidx.test.uiautomator:uiautomator:${Versions.uiautomator}"
    const val rules = "androidx.test:rules:${Versions.rules}"
    const val mockwebserver = "com.squareup.okhttp3:mockwebserver:${Versions.mockwebserver}"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    const val coreTesting = "android.arch.core:core-testing:${Versions.coreTesting}"
}
