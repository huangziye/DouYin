package com.hzy.douyin

import android.annotation.TargetApi
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.hzy.douyinlib.listener.OnViewPagerListener
import com.hzy.douyinlib.widget.FullScreenVideoView
import com.hzy.douyinlib.widget.RecyclerLinearLayoutManager

class MainActivity : AppCompatActivity() {
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mRecyclerLinearLayoutManager: RecyclerLinearLayoutManager
    private lateinit var mAdapter: RecyclerAdapter
    private val images = intArrayOf(
        R.mipmap.img_video_1,
        R.mipmap.img_video_2,
        R.mipmap.img_video_3,
        R.mipmap.img_video_4,
        R.mipmap.img_video_5,
        R.mipmap.img_video_6,
        R.mipmap.img_video_7,
        R.mipmap.img_video_8
    )
    private val videos = intArrayOf(
        R.raw.video_1,
        R.raw.video_2,
        R.raw.video_3,
        R.raw.video_4,
        R.raw.video_5,
        R.raw.video_6,
        R.raw.video_7,
        R.raw.video_8
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mRecyclerView = findViewById(R.id.recyclerView)
        mRecyclerLinearLayoutManager = RecyclerLinearLayoutManager(this, RecyclerView.VERTICAL, false)
        mRecyclerView.layoutManager = mRecyclerLinearLayoutManager
        mAdapter = RecyclerAdapter(this, images, videos)
        mRecyclerView.adapter = mAdapter

        initListener()
    }

    fun initListener() {
        mRecyclerLinearLayoutManager.setOnViewPagerListener(object : OnViewPagerListener {
            override fun onInitComplete() {

            }

            override fun onPageRelease(isNext: Boolean, position: Int) {
                Log.e("TAG", "释放位置:$position 下一页:$isNext")
                var index = if (isNext) 0 else 1
                releaseVideo(index)
            }

            override fun onPageSelected(isBottom: Boolean, position: Int) {
                Log.e("TAG", "选择位置:$position 下一页:$isBottom")
                playVideo(0)
            }

        })
    }

    fun releaseVideo(position: Int) {
        val itemView = mRecyclerView.getChildAt(position)
        val videoView = itemView.findViewById<FullScreenVideoView>(R.id.videoView)
        val img = itemView.findViewById<ImageView>(R.id.img)
        val imgPlay = itemView.findViewById<ImageView>(R.id.img_play)
        videoView.stopPlayback()
        img.animate().alpha(1f).start()
        imgPlay.animate().alpha(0f).start()
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun playVideo(position: Int) {
        val itemView = mRecyclerView.getChildAt(position)
        val videoView = itemView.findViewById<FullScreenVideoView>(R.id.videoView)
        val img = itemView.findViewById<ImageView>(R.id.img)
        val imgPlay = itemView.findViewById<ImageView>(R.id.img_play)
        var mediaPlayer: MediaPlayer? = MediaPlayer()

        videoView.setOnPreparedListener { mp ->
            println("........")
        }

        videoView.setOnInfoListener { mp, what, extra ->
            mediaPlayer = mp
            mp?.isLooping = true
            img.animate().alpha(0f).setDuration(200).start()
            false
        }

        videoView.start()
        imgPlay.setOnClickListener(object : View.OnClickListener {
            var isPlaying = true
            override fun onClick(v: View?) {
                isPlaying = if (videoView.isPlaying) {
                    imgPlay.animate().alpha(0.7f).start()
                    videoView.pause()
                    false
                } else {
                    imgPlay.animate().alpha(0f).start()
                    videoView.start()
                    true
                }
            }
        })
    }

}
