plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinSerialization)
    kotlin(Plugins.kapt)
}

android {
    namespace = "com.place_details.data"
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
    implementation(project(Modules.commonNetwork))
    implementation(project(Modules.commonPlannerFeatureData))

    implementation(project(Modules.commonFeaturesPlaceDetailsDomain))

    // Lifecycle
    implementation(Dependencies.Lifecycle.core)

    // Retrofit
    implementation(Dependencies.Retrofit.retrofit)
    implementation(Dependencies.Retrofit.serialization)

    // Serialization
    implementation(Dependencies.Serialization.kotlinSerializationJson)

    // Room
    implementation(Dependencies.Room.runtime)
    kapt(Dependencies.Room.compiler)
    implementation(Dependencies.Room.ktx)
}