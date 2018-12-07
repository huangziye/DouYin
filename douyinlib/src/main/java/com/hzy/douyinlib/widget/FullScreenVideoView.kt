package com.hzy.douyinlib.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.VideoView

/**
 * Created by ziye_huang on 2018/12/7.
 */
open class FullScreenVideoView : VideoView {

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // This has been modified
        var width = getDefaultSize(0, widthMeasureSpec)
        var height = getDefaultSize(0, heightMeasureSpec)
        setMeasuredDimension(width, height)
    }

}