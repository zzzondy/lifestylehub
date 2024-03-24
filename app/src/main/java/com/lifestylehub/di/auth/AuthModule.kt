package com.lifestylehub.di.auth

import android.content.SharedPreferences
import android.util.Log
import com.auth.data.authorization.AuthDatabaseManager
import com.auth.data.local.LocalAuthRepository
import com.auth.data.local.LocalAuthRepositoryImpl
import com.auth.data.remote.network.AuthNetworkService
import com.auth.data.remote.repository.RemoteAuthRepository
import com.auth.data.remote.repository.RemoteAuthRepositoryImpl
import com.auth.data.repository.AuthRepositoryImpl
import com.auth.domain.repository.AuthRepository
import com.common.network.BuildConfig
import com.common.network.di.qualifiers.RandomUsersRetrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.lifestylehub.di.local_storage.qualifiers.AuthSharedPreferences
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class AuthModule {

    init {
        Log.d("Auth", BuildConfig.RANDOMUSER_API_URL)
    }

    private val json = Json {
        ignoreUnknownKeys = true
    }

    @RandomUsersRetrofit
    @Singleton
    @Provides
    fun provideAuthRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.RANDOMUSER_API_URL)
        .client(client)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    @Singleton
    @Provides
    fun provideAuthNetworkService(@RandomUsersRetrofit retrofit: Retrofit): AuthNetworkService =
        retrofit.create(AuthNetworkService::class.java)

    @Singleton
    @Provides
    fun provideRemoteAuthRepository(authNetworkService: AuthNetworkService): RemoteAuthRepository =
        RemoteAuthRepositoryImpl(authNetworkService)


    @Singleton
    @Provides
    fun provideLocalAuthRepository(@AuthSharedPreferences sharedPreferences: SharedPreferences): LocalAuthRepository =
        LocalAuthRepositoryImpl(sharedPreferences)

    @Singleton
    @Provides
    fun provideAuthRepository(
        localAuthRepository: LocalAuthRepository,
        authDatabaseManager: AuthDatabaseManager,
        remoteAuthRepository: RemoteAuthRepository
    ): AuthRepository = AuthRepositoryImpl(localAuthRepository, remoteAuthRepository, authDatabaseManager)
}