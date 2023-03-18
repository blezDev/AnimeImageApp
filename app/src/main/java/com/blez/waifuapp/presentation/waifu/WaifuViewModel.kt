package com.blez.waifuapp.presentation.waifu

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blez.waifuapp.data.model.AnimeImage
import com.blez.waifuapp.repositroy.ImageRepository
import com.blez.waifuapp.util.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WaifuViewModel @Inject constructor(val repo: ImageRepository) : ViewModel() {
    sealed class SetupEvent {
        object Loading : SetupEvent()
        data class ImageData(val data: AnimeImage) : SetupEvent()
    }




    var showNSFW = mutableStateOf(false)
    var imageData = mutableStateOf<String>("")
    var isLoading = mutableStateOf(false)
    var loadError = mutableStateOf("")

    fun switch_NSFW(value : Boolean){
       showNSFW.value = value
    }

    init {
        getImage()
    }


    fun getImage(category: String? = "waifu") {
        isLoading.value = true
        viewModelScope.launch {
            val result = category?.let {
                if (!showNSFW.value)
                    repo.getSFWImage(it)
                else
                    repo.getNsfwImage(category)
            }
            when (result) {
                is ResultState.Success -> {
                    isLoading.value = false
                    val data = result.data
                    imageData.value = data?.url?:return@launch


                }
                is ResultState.Failure->{
                    isLoading.value = false
                    loadError.value = result.message.toString()
                }
                else-> Unit
            }
        }


    }
}