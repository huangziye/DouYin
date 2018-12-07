package com.hzy.douyinlib.widget

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.hzy.douyinlib.listener.OnViewPagerListener

/**
 * Created by ziye_huang on 2018/12/7.
 */
class RecyclerLinearLayoutManager : LinearLayoutManager, RecyclerView.OnChildAttachStateChangeListener {

    private var mPagerSnapHelper: PagerSnapHelper = PagerSnapHelper()
    private var mViewPagerListener: OnViewPagerListener? = null
    private var diffY = 0

    constructor(context: Context) : super(context)

    constructor(
        context: Context, @RecyclerView.Orientation orientation: Int,
        reverseLayout: Boolean
    ) : super(context, orientation, reverseLayout)

    override fun onAttachedToWindow(view: RecyclerView?) {
        super.onAttachedToWindow(view)
        view?.addOnChildAttachStateChangeListener(this)
        mPagerSnapHelper.attachToRecyclerView(view)
    }

    override fun onChildViewDetachedFromWindow(view: View) {
        val position = getPosition(view)
        mViewPagerListener?.onPageRelease(0 < diffY, position)
    }

    override fun onChildViewAttachedToWindow(view: View) {
        val position = getPosition(view)
        if (0 == position) {
            mViewPagerListener?.onPageSelected(false, position)
        }
    }

    override fun onScrollStateChanged(state: Int) {
        if (state == RecyclerView.SCROLL_STATE_IDLE) {
            val view = mPagerSnapHelper.findSnapView(this)
            if (null != view) {
                val position = getPosition(view)
                mViewPagerListener?.onPageSelected(position == itemCount - 1, position)
            }
        }
        super.onScrollStateChanged(state)
    }

    fun setOnViewPagerListener(listener: OnViewPagerListener) {
        mViewPagerListener = listener
    }

    override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        diffY = dy
        return super.scrollVerticallyBy(dy, recycler, state)
    }

}