package com.masterandroid.android_mvp.view

import com.masterandroid.android_mvp.model.Post

interface UpdateView {

    fun onUpdatePostSuccess(post:Post?)
    fun onUpdatePostFailure(error:String)


}