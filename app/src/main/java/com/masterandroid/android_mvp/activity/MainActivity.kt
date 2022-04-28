package com.masterandroid.android_mvp.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android_mvc.utils.Utils
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.masterandroid.android_mvp.R
import com.masterandroid.android_mvp.adapter.PostAdapter
import com.masterandroid.android_mvp.model.Post
import com.masterandroid.android_mvp.presenter.MainPresenter
import com.masterandroid.android_mvp.view.MainView

class MainActivity : AppCompatActivity(), MainView {
    private lateinit var recyclerView: RecyclerView
    lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        mainPresenter = MainPresenter(this)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.layoutManager = GridLayoutManager(this, 1)

        val b_create: FloatingActionButton = findViewById(R.id.f_button)
        b_create.setOnClickListener {
            openCreateActivity()
        }
        Utils.setItemTouchHelperRightToLeft(this, recyclerView)
        mainPresenter.apiPostList()

    }

    private fun refreshAdapter(posters: ArrayList<Post>) {
        val adapter = PostAdapter(this, posters)
        recyclerView.adapter = adapter
    }

    override fun onPostListSuccess(posts: ArrayList<Post>?) {
        refreshAdapter(posts!!)
    }

    override fun onPostListFailure(error: String) {
        Toast.makeText(this, "Something is wrong", Toast.LENGTH_SHORT).show()
    }

    override fun onPostDeleteSuccess(post: Post?) {
        mainPresenter.apiPostList()
    }

    override fun onPostDeleteFailure(error: String) {
        Toast.makeText(this, "Something is wrong", Toast.LENGTH_SHORT).show()
    }

    private fun openCreateActivity() {
        val intent = Intent(this, CreateActivity::class.java)
        createLauncher.launch(intent)
    }

    private var createLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            mainPresenter.apiPostList()
        }
    }

    fun openUpdateActivity(post: Post) {
        val intent = Intent(this, UpdateActivity::class.java)
        intent.putExtra("post", post)
        updateLauncher.launch(intent)
    }

    var updateLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            mainPresenter.apiPostList()

        }
    }

}