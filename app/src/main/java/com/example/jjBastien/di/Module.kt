package com.example.jjBastien.di

import com.example.jjBastien.network.CountryRepository
import com.example.jjBastien.network.CountryRepositoryImpl
import com.example.jjBastien.network.ServiceApi
import com.example.jjBastien.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Module {

    @Singleton
    @Provides
    fun providesServiceApi(): ServiceApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(providesOkhttpClient())
            .build()
            .create(ServiceApi::class.java)

    @Singleton
    @Provides
    fun providesOkhttpClient() :OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply{
                level = HttpLoggingInterceptor.Level.BODY
            })
            .connectTimeout(30,TimeUnit.SECONDS)
            .readTimeout(30,TimeUnit.SECONDS)
            .writeTimeout(30,TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    fun providesCountryRepository(): CountryRepository =
        CountryRepositoryImpl(providesServiceApi())

}