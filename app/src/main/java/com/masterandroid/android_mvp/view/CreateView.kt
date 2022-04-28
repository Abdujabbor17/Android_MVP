package com.masterandroid.android_mvp.view

import com.masterandroid.android_mvp.model.Post

interface CreateView {

    fun onCreatePostSuccess(post:Post?)
    fun onCreatePostFailure(error:String)


}