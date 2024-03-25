plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    kotlin(Plugins.kapt)
}

android {
    namespace = "com.common.network"
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
                "OPEN_WEATHER_API_URL",
                "\"https://api.openweathermap.org/\""
            )
            buildConfigField(
                "String",
                "FOUR_SQUARE_API_URL",
                "\"https://api.foursquare.com/v2/\""
            )
            buildConfigField(
                "String",
                "RANDOMUSER_API_URL",
                "\"https://randomuser.me/\""
            )
            buildConfigField(
                "String",
                "BORED_API_URL",
                "\"https://www.boredapi.com/api/\""
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
                "OPEN_WEATHER_API_URL",
                "\"https://api.openweathermap.org/\""
            )
            buildConfigField(
                "String",
                "FOUR_SQUARE_API_URL",
                "\"https://api.foursquare.com/v2/\""
            )
            buildConfigField(
                "String",
                "RANDOMUSER_API_URL",
                "\"https://randomuser.me/\""
            )
            buildConfigField(
                "String",
                "BORED_API_URL",
                "\"https://www.boredapi.com/api/\""
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        buildConfig = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    // Dagger
    implementation(Dependencies.Dagger.dagger)
    kapt(Dependencies.Dagger.compiler)

    // OkHttp3
    api(Dependencies.OkHttp3.okhttp)
    debugApi(Dependencies.OkHttp3.loggingInterceptor)

    // Retrofit
    api(Dependencies.Retrofit.retrofit)
    api(Dependencies.Retrofit.serialization)

    // Serialization
    api(Dependencies.Serialization.kotlinSerializationJson)

    // Google Services
    api(Dependencies.GoogleServices.location)
}