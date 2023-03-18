package com.blez.waifuapp.repositroy

import com.blez.waifuapp.data.api.WaifuAPI
import com.blez.waifuapp.data.model.AnimeImage
import com.blez.waifuapp.util.ResultState

class ImageRepository(val api : WaifuAPI)  {
    suspend fun getSFWImage(category : String) : ResultState<AnimeImage> {
        return try {
                val result = api.getSFWImage(category)
                ResultState.Success(result.body()!!)
        }catch (e : Exception){
            ResultState.Failure(message = e.message)
        }
    }

    suspend fun getNsfwImage(category: String) : ResultState<AnimeImage>{
        return try {
            val result = api.getNSFWImage(category)
            ResultState.Success(result.body())
        }catch (e : Exception){
            ResultState.Failure(e.message)
        }
    }


}