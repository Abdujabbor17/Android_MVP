package com.masterandroid.android_mvp.presenter

import com.masterandroid.android_mvp.model.Post
import com.masterandroid.android_mvp.service.RetrofitHttp
import com.masterandroid.android_mvp.view.CreateView
import com.masterandroid.android_mvp.view.MainView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreatePresenter(var createView: CreateView) : CreatePresenterImpl {

    override fun apiCreatePost(post: Post) {
        RetrofitHttp.postService.cratePost(post).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                createView.onCreatePostSuccess(response.body())
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
               createView.onCreatePostFailure(t.toString())
            }

        })
    }



}