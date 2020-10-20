package com.maiz.memesland

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface MyApi {


    object ApiService {
        val memeInstance: MyApi

        init {
            val retrofit = Retrofit.Builder().baseUrl("https://meme-api.herokuapp.com/gimme/")
                .addConverterFactory(GsonConverterFactory.create()).build()
            memeInstance = retrofit.create(MyApi::class.java)
        }
    }

    @GET("{number}")
    fun getpost(@Path("number") number: Int): Call<ApiResponse>
}