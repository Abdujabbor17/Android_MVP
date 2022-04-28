package com.masterandroid.android_mvp.service
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitHttp {
    private val IS_TESTER = true
    private val SERVER_DEVELOPMENT = "https://6261a562327d3896e27e6366.mockapi.io/api/"
    private val SERVER_PRODUCTION = "https://jsonplaceholder.typicode.com/"

    val retrofit = Retrofit.Builder().baseUrl(server()).addConverterFactory(GsonConverterFactory.create()).build()

    private fun server(): String {
        if (IS_TESTER) return SERVER_DEVELOPMENT
        return SERVER_PRODUCTION
    }

    val postService: PostService = retrofit.create(PostService::class.java)
}