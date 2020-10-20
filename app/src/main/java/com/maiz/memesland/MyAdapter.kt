package com.maiz.memesland

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyAdapter(var memeList: List<memes>,var context:Context) : RecyclerView.Adapter<MyAdapter.MyAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.iteml_ist, parent, false)
        return MyAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyAdapterViewHolder, position: Int) {
        val model=memeList[position]
        Glide.with(context).load(model.url).into(holder.imageview)
    }

    override fun getItemCount(): Int {
        return memeList.size
    }

    class MyAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageview = itemView.findViewById<ImageView>(R.id.imageview)
    }
}