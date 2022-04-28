package com.masterandroid.android_mvp.presenter

import com.masterandroid.android_mvp.model.Post

interface MainPresenterImpl {
    fun apiPostList()
    fun apiPostDelete(post:Post)
}