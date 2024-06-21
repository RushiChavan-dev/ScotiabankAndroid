package com.scotiabank.assignment.di

import com.scotiabank.assignment.network.UserService
import com.scotiabank.assignment.network.model.RepoDtoMapper
import com.scotiabank.assignment.network.model.UserDtoMapper
import com.google.gson.GsonBuilder
import com.scotiabank.assignment.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Dagger Hilt module for providing network-related dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * Provides the UserDtoMapper dependency.
     */
    @Singleton
    @Provides
    fun provideUserDtoMapper(): UserDtoMapper {
        return UserDtoMapper()
    }

    /**
     * Provides the RepoDtoMapper dependency.
     */
    @Singleton
    @Provides
    fun provideRepoDtoMapper(): RepoDtoMapper {
        return RepoDtoMapper()
    }



    /**
     * Provides the UserService dependency.
     */
    @Singleton
    @Provides
    fun provideUserService(): UserService {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer ${BuildConfig.API_KEY}")
                    .build()
                chain.proceed(request)
            }
            .build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(UserService::class.java)
    }
}
