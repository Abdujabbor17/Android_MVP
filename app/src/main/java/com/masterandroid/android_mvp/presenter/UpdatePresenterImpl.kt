package com.masterandroid.android_mvp.presenter

import com.masterandroid.android_mvp.model.Post

interface UpdatePresenterImpl {
    fun apiUpdatePost(post: Post)
}