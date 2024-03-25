object Dependencies {

    object Compose {
        const val version = "1.5.10"
        private const val bomVersion = "2024.02.02"

        const val bom = "androidx.compose:compose-bom:$bomVersion"
        const val ui = "androidx.compose.ui:ui"
        const val uiGraphics = "androidx.compose.ui:ui-graphics"
        const val toolingPreview = "androidx.compose.ui:ui-tooling-preview"
        const val material3 = "androidx.compose.material3:material3"

        const val navigation = "androidx.navigation:navigation-compose:2.7.7"

        const val constraintLayout = "androidx.constraintlayout:constraintlayout-compose:1.0.1"
    }

    object Lifecycle {
        const val core = "androidx.core:core-ktx:1.12.0"
        const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:2.7.0"
        const val composeActivity = "androidx.activity:activity-compose:1.8.2"
    }

    object Testing {
        const val junit = "junit:junit:4.13.2"
        const val androidJunit = "androidx.test.ext:junit:1.1.5"
        const val espresso = "androidx.test.espresso:espresso-core:3.5.1"
        const val composeJunit = "androidx.compose.ui:ui-test-junit4"
        const val composeUiTolling = "androidx.compose.ui:ui-tooling"
        const val composeTestManifest = "androidx.compose.ui:ui-test-manifest"
        const val mockk = "io.mockk:mockk:1.13.10"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4"
    }

    object Serialization {
        const val kotlinSerialization = "org.jetbrains.kotlin:kotlin-serialization:1.9.22"
        const val kotlinSerializationJson = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0"
    }

    object OkHttp3 {
        private const val okHttpVersion = "4.9.0"

        const val okhttp = "com.squareup.okhttp3:okhttp:$okHttpVersion"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$okHttpVersion"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
        const val serialization =
            "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0"
    }

    object Dagger {
        private const val daggerVersion = "2.51"

        const val dagger = "com.google.dagger:dagger:$daggerVersion"
        const val compiler = "com.google.dagger:dagger-compiler:$daggerVersion"
    }

    object GoogleServices {
        const val location = "com.google.android.gms:play-services-location:21.2.0"
    }

    object Coroutines {
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0"
    }

    object Coil {
        const val compose = "io.coil-kt:coil-compose:2.6.0"
    }

    object Room {
        private const val roomVersion = "2.5.0"

        const val runtime = "androidx.room:room-runtime:$roomVersion"
        const val compiler = "androidx.room:room-compiler:$roomVersion"
        const val ktx = "androidx.room:room-ktx:$roomVersion"
    }

    object SqlCipher {
        const val sqlCipher = "net.zetetic:android-database-sqlcipher:4.5.3"
        const val sqlite = "androidx.sqlite:sqlite:2.1.0"
    }

    object Crypto {

        const val crypto = "androidx.security:security-crypto-ktx:1.1.0-alpha06"
    }

}