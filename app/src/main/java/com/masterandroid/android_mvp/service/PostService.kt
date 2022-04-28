package com.masterandroid.android_mvp.service

import com.masterandroid.android_mvp.model.Post
import retrofit2.Call
import retrofit2.http.*

interface PostService {


    @GET("user")
    fun listPost(): Call<ArrayList<Post>>

    @DELETE("user/{id}")
    fun deletePost(@Path("id") id: Int): Call<Post>

    @POST("user")
    fun cratePost(@Body post: Post): Call<Post>

    @PUT("user/{id}")
    fun updatePost(@Path("id") id: Int,@Body post: Post): Call<Post>

}