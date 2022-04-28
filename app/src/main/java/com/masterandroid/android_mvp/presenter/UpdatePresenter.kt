package com.masterandroid.android_mvp.presenter

import com.masterandroid.android_mvp.model.Post
import com.masterandroid.android_mvp.service.RetrofitHttp
import com.masterandroid.android_mvp.view.CreateView
import com.masterandroid.android_mvp.view.MainView
import com.masterandroid.android_mvp.view.UpdateView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdatePresenter(var updateView: UpdateView) : UpdatePresenterImpl {

    override fun apiUpdatePost(post: Post) {
        RetrofitHttp.postService.updatePost(post.id!!,post).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                updateView.onUpdatePostSuccess(response.body())
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                updateView.onUpdatePostFailure(t.toString())
            }

        })
    }


}