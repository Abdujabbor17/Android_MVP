package com.masterandroid.android_mvp.model

import java.io.Serializable

class Post:Serializable{
    var id:Int?=null
    var title:String
    var body:String

    constructor(title: String,body: String) {
        this.body = body
        this.title = title
    }

}