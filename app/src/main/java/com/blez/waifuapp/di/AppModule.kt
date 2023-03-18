package com.blez.waifuapp.di

import com.blez.waifuapp.data.api.WaifuAPI
import com.blez.waifuapp.repositroy.ImageRepository
import com.blez.waifuapp.util.Constants.BASE_URL
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
object AppModule {


    @Provides
    @Singleton
    fun provideOkHttpClient() : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES) // write timeout
            .readTimeout(1, TimeUnit.MINUTES) // read timeout
            .build()

    }


    @Provides
    @Singleton
    fun providesPokemonAPI(okHttpClient: OkHttpClient) : WaifuAPI{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(WaifuAPI::class.java)
    }


    @Provides
    @Singleton
    fun providesImageRepository(animeAPI : WaifuAPI) : ImageRepository{
        return ImageRepository(animeAPI)
    }
}