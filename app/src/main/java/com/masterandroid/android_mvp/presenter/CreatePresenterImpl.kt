package com.masterandroid.android_mvp.presenter

import com.masterandroid.android_mvp.model.Post

interface CreatePresenterImpl {
    fun apiCreatePost(post: Post)
}