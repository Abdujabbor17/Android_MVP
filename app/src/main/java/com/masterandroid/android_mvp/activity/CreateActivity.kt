package com.masterandroid.android_mvp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.masterandroid.android_mvp.R
import com.masterandroid.android_mvp.model.Post
import com.masterandroid.android_mvp.presenter.CreatePresenter
import com.masterandroid.android_mvp.view.CreateView

class CreateActivity : AppCompatActivity() ,CreateView{
    var TAG = CreateActivity::class.java.simpleName
    lateinit var createPresenter: CreatePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
        initViews()
    }

    private fun initViews() {
        createPresenter = CreatePresenter(this)
        val et_title = findViewById<EditText>(R.id.et_title)
        val et_body = findViewById<EditText>(R.id.et_body)
        val b_cancel = findViewById<Button>(R.id.btn_cancel)
        val b_post = findViewById<Button>(R.id.btn_post)
        b_cancel.setOnClickListener {
            finish()
        }
        b_post.setOnClickListener {
            val title = et_title.text.toString()
            val body = et_body.text.toString()
            val post = Post( title, body)
            createPresenter.apiCreatePost(post)
        }
    }



    override fun onCreatePostSuccess(post: Post?) {
        val intent = Intent()
        setResult(RESULT_OK,intent)
        finish()
    }

    override fun onCreatePostFailure(error: String) {

        Toast.makeText(this, "Please try again", Toast.LENGTH_SHORT).show()
        finish()
    }
}