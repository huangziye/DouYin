package com.hzy.douyin

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.hzy.douyinlib.widget.FullScreenVideoView

/**
 * Created by ziye_huang on 2018/12/7.
 */
class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var img: ImageView = itemView.findViewById(R.id.img)
    var videoView: FullScreenVideoView = itemView.findViewById(R.id.videoView)
}