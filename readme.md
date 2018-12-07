
[![](https://jitpack.io/v/huangziye/DouYin.svg)](https://jitpack.io/#huangziye/DouYin)


# Add ` DouYin ` to project

- Step 1：Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

```android
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

- Step 2：Add the dependency

The latest version shall prevail.

```android
dependencies {
        implementation 'com.github.huangziye:DouYin:${latest_version}'
}
```




# Effect picture


![圆形图片效果图](https://github.com/huangziye/DouYin/blob/master/screenshot/DouYin.png)


# Usage

```Kotlin

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
```







# About me


- [简书](https://user-gold-cdn.xitu.io/2018/7/26/164d5709442f7342)

- [掘金](https://juejin.im/user/5ad93382518825671547306b)

- [Github](https://github.com/huangziye)


# License

```
Copyright 2018, huangziye

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```



