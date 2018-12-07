package com.hzy.douyin

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by ziye_huang on 2018/12/7.
 */
class RecyclerAdapter(val context: Context, private val images: IntArray, private val videos: IntArray) :
    RecyclerView.Adapter<RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_view_pager, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return videos.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        //在此处可以获取视频的第一帧，然后显示
        holder.img.setImageResource(images[position])
        holder.videoView.setVideoURI(Uri.parse("android.resource://" + context.packageName + "/" + videos[position]))
    }

}