plugins {
    id(Plugins.kotlinAndroid)
    id(Plugins.androidLibrary)
    id(Plugins.kotlinSerialization)
}

android {
    namespace = "com.main.data"
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            buildConfigField(
                "String",
                "OPEN_WEATHER_API_KEY",
                "\"9626822fe842569ada7ecfb5c222cb17\""
            )

            buildConfigField(
                "String",
                "FOUR_SQUARE_API_KEY",
                "\"IFXBK2WNI5GH2XEKI0DSIW0HKZKJTZLQSOOFGYXG1OJTA03W\""
            )

            buildConfigField(
                "String",
                "FOUR_SQUARE_VERSION",
                "\"20231010\""
            )
        }

        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField(
                "String",
                "OPEN_WEATHER_API_KEY",
                "\"9626822fe842569ada7ecfb5c222cb17\""
            )
            buildConfigField(
                "String",
                "FOUR_SQUARE_API_KEY",
                "\"IFXBK2WNI5GH2XEKI0DSIW0HKZKJTZLQSOOFGYXG1OJTA03W\""
            )

            buildConfigField(
                "String",
                "FOUR_SQUARE_VERSION",
                "\"20231010\""
            )
        }
    }

    buildFeatures {
        buildConfig = true
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

    // Modules
    implementation(project(Modules.featuresMainDomain))
    implementation(project(Modules.commonNetwork))

    // Lifecycle
    implementation(Dependencies.Lifecycle.core)

    // Retrofit
    implementation(Dependencies.Retrofit.retrofit)
    implementation(Dependencies.Retrofit.serialization)

    // Local storage
    implementation(Dependencies.LocalStorage.dataStore)

    // Serialization
    implementation(Dependencies.Serialization.kotlinSerializationJson)
}