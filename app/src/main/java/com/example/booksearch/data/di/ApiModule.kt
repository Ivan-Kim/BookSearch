package com.example.booksearch.data.di

import com.example.booksearch.data.network.NaverApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import com.example.booksearch.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun providesNaverInterceptor(): Interceptor = Interceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader("X-Naver-Client-Id", BuildConfig.ClientId)
            .addHeader("X-Naver-Client-Secret", BuildConfig.ClientSecret)
            .build()
        chain.proceed(request)
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(naverInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(naverInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofitClient(client: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesNaverApiService(retrofit: Retrofit): NaverApiService =
        retrofit.create(NaverApiService::class.java)

}