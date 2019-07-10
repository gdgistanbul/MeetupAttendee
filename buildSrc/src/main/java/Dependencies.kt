object CoreLibraries {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlinVersion}"

    const val coroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesVersion}"
    const val koin = "org.koin:koin-android:${Versions.coinVersion}"
    const val koinViewModel = "org.koin:koin-android-viewmodel:${Versions.coinVersion}"
}


object ThirdPartyLibraries {
    const val retrofit = "com.squareup.retrofit2:retrofit:2.6.0"
    const val moshi = "com.squareup.moshi:moshi-kotlin:${Versions.moshiVersion}"
    const val moshiapt = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshiVersion}"
    const val moshiConverter = "com.squareup.retrofit2:converter-moshi:2.5.0"
}

object AndroidxLibraries {
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    const val core = "androidx.core:core-ktx:${Versions.appCompatVersion}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    const val lifecycleExtensions =
        "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleVersion}"
    const val viewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}"
    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigationVersion}"
    const val navigationUI =
        "androidx.navigation:navigation-ui-ktx:${Versions.navigationVersion}"
}

object FirebaseLibraries {
    const val core = "com.google.firebase:firebase-core:${Versions.firebaseVersion}"
    const val database = "com.google.firebase:firebase-database:${Versions.firebaseVersion}"
}

object TestLibraries {
    const val jUnit = "junit:junit:${Versions.jUnitVersion}"
    const val runnner = "com.android.support.test:runner:${Versions.testRunnerVersion}"
    const val espressoCore =
        "com.android.support.test.espresso:espresso-core:${Versions.espressoCoreVersion}"
    const val mockk = "io.mockk:mockk:${Versions.mockKversion}"

    const val archCoreTesting = "android.arch.core:core-testing:1.0.0"
    const val coroutinesTesting =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesVersion}"
}