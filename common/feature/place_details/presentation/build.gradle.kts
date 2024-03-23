plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    kotlin(Plugins.kapt)
}

android {
    namespace = "com.place_details.presentation"
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
        }

        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.Compose.version
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
    implementation(project(Modules.commonUi))
    implementation(project(Modules.commonNavigation))
    implementation(project(Modules.commonNetwork))

    api(project(Modules.commonFeaturesPlaceDetailsDomain))
    api(project(Modules.commonFeaturesPlaceDetailsData))

    // Compose
    implementation(platform(Dependencies.Compose.bom))
    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.material3)
    implementation(Dependencies.Compose.constraintLayout)
    implementation(Dependencies.Compose.uiGraphics)
    implementation(Dependencies.Compose.toolingPreview)
    implementation(Dependencies.Compose.navigation)


    // Lifecycle
    implementation(Dependencies.Lifecycle.composeActivity)

    // Coil
    implementation(Dependencies.Coil.compose)

    // Dagger
    implementation(Dependencies.Dagger.dagger)
    kapt(Dependencies.Dagger.compiler)
}