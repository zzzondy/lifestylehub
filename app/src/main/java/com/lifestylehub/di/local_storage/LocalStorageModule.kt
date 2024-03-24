package com.lifestylehub.di.local_storage

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.auth.data.authorization.AuthDatabaseManager
import com.feature_main.presentation.di.qualifiers.PermissionsSharedPreference
import com.lifestylehub.di.local_storage.qualifiers.AuthEncryptedSharedPreferences
import com.lifestylehub.di.local_storage.qualifiers.AuthSharedPreferences
import com.place_details.data.local.database.PlaceDetailsDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalStorageModule {

    @PermissionsSharedPreference
    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences(PERMISSIONS_PREFERENCES, Context.MODE_PRIVATE)

    @AuthEncryptedSharedPreferences
    @Singleton
    @Provides
    fun provideAuthEncryptedSharedPreferences(context: Context): SharedPreferences {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        return EncryptedSharedPreferences(
            context,
            AUTH_ENCRYPTED_PREFERENCES,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    @AuthSharedPreferences
    @Singleton
    @Provides
    fun provideAuthSharedPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences(AUTH_PREFERENCES, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideAuthDatabaseManager(
        context: Context,
        @AuthEncryptedSharedPreferences encryptedSharedPreferences: SharedPreferences
    ): AuthDatabaseManager = AuthDatabaseManager(encryptedSharedPreferences, context)

    @Singleton
    @Provides
    fun provideRoomDatabase(context: Context): PlaceDetailsDatabase =
        PlaceDetailsDatabase.create(context)
}

private const val PERMISSIONS_PREFERENCES = "PERMISSIONS_PREFERENCES"
private const val AUTH_ENCRYPTED_PREFERENCES = "AUTH_ENCRYPTED_PREFERENCES"
private const val AUTH_PREFERENCES = "AUTH_PREFERENCES"
