package com.masterandroid.android_mvp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android_mvc.utils.Utils
import com.masterandroid.android_mvp.activity.MainActivity
import com.masterandroid.android_mvp.R
import com.masterandroid.android_mvp.model.Post
import java.util.*
import kotlin.collections.ArrayList

class PostAdapter (var activity: MainActivity, var items:ArrayList<Post>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_post,parent,false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val post: Post = items[position]

        if (holder is PostViewHolder){
            holder.iv_delete.setOnClickListener {
               deletePostDialog(post)
            }
             holder.iv_edit.setOnClickListener {
                 activity.openUpdateActivity(post)
            }


            holder.tv_title.text = post.title.uppercase(Locale.getDefault())
            holder.tv_body.text = post.body

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class PostViewHolder(view: View): RecyclerView.ViewHolder(view){
        var tv_title : TextView = view.findViewById(R.id.tv_title)
        var tv_body : TextView = view.findViewById(R.id.tv_body)
        var iv_delete:ImageView  = view.findViewById(R.id.iv_delete)
        var iv_edit:ImageView  = view.findViewById(R.id.iv_edit)
    }

    fun deletePostDialog(post: Post) {
        val title = "Delete"
        val body = "Do you want to delete?"
        Utils.customDialog(activity, title, body, object : Utils.DialogListener {
            override fun onPositiveClick() {
               activity.mainPresenter.apiPostDelete(post)
            }

            override fun onNegativeClick() {

            }
        })
    }
}