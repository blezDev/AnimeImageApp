package com.blez.waifuapp.util

sealed class ResultState<T>(data : T?,message : String?= null) {
 data  class Success<T>(val data: T?) :ResultState<T>(data)
   data class Failure<T>(val message: String? = null,val data: T?=null) : ResultState<T>(data,message)


}