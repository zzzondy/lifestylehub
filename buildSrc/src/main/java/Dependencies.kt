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
    }

    object Serialization {
        const val kotlinSerialization = "org.jetbrains.kotlin:kotlin-serialization:1.9.22"
        const val kotlinSerializationJson = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
        const val serialization =
            "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0"
    }
}