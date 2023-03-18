package com.blez.waifuapp.data.api

import com.blez.waifuapp.data.model.AnimeImage
import com.blez.waifuapp.util.ResultState
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WaifuAPI {
    @GET("/sfw/{category}")
    suspend fun getSFWImage(@Path("category") category : String) : Response<AnimeImage>

    @GET("/nsfw/{category}")
    suspend fun getNSFWImage(@Path("category") category: String) : Response<AnimeImage>

}