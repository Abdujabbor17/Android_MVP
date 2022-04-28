package com.masterandroid.android_mvp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.masterandroid.android_mvp.R
import com.masterandroid.android_mvp.model.Post
import com.masterandroid.android_mvp.presenter.UpdatePresenter
import com.masterandroid.android_mvp.view.UpdateView

class UpdateActivity : AppCompatActivity(),UpdateView {
    lateinit var et_title: EditText
    lateinit var post: Post
    lateinit var et_body: EditText
    lateinit var updatePresenter: UpdatePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        initViews()
    }

    private fun initViews() {
        updatePresenter = UpdatePresenter(this)
        et_body = findViewById(R.id.et_body)
        et_title = findViewById(R.id.et_title)
        setData()
        val b_cancel = findViewById<Button>(R.id.btn_cancel).also {
            it.setOnClickListener { finish() }
        }
        val b_update = findViewById<Button>(R.id.btn_update).also {
            it.setOnClickListener { updatePost() }
        }
    }

    private fun updatePost() {
        post.body = et_body.text.toString()
        post.title = et_title.text.toString()
        updatePresenter.apiUpdatePost(post)
    }

    private fun setData() {
        post = intent.getSerializableExtra("post") as Post
        et_title.setText(post.title)
        et_body.setText(post.body)
    }



    override fun onUpdatePostSuccess(post: Post?) {
        val intent = Intent()
        setResult(RESULT_OK, intent)
        finish()
    }

    override fun onUpdatePostFailure(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        finish()
    }
}