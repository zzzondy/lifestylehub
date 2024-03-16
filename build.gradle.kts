buildscript {
    dependencies {
        classpath(Dependencies.Serialization.kotlinSerialization)
        classpath(Dependencies.Hilt.daggerHiltProject)
    }
}

plugins {
    id(Plugins.androidApplication) version Plugins.androidApplicationVersion apply false
    id(Plugins.kotlinAndroid) version Plugins.kotlinVersion apply false
}