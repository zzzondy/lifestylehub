buildscript {
    dependencies {
        classpath(Dependencies.Serialization.kotlinSerialization)
    }
}

plugins {
    id(Plugins.androidApplication) version Plugins.androidApplicationVersion apply false
    id(Plugins.kotlinAndroid) version Plugins.kotlinVersion apply false
}