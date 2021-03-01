package com.example.myapplication.widget

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.listview.dp


/**
 * @author hql
 * @date 21-01-27
 * desc:带有阻尼效果的滚动器
 */
class DampingScrollLayout @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {
    private var childView: RecyclerView? = null
    private val original = Rect()
    private var isMoved = false
    private var startYpos = 0f
    private var lastYpos = 0f
    private var isSuccess = false
    private var canRebound = true  //是否可以回弹
    private var isDragging = false  //是否user拖动
    private var isDown = false  //是否按下
    private var mScrollListener: ScrollListener? = null
    override fun onFinishInflate() {
        super.onFinishInflate()
        childView = getChildAt(0) as RecyclerView
        if(childView == null){
            throw RuntimeException("此控件需要设置RecyclerView子类！")
        }

        childView!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
          override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
              isDragging = newState == RecyclerView.SCROLL_STATE_DRAGGING
                //如果是是人拖动的情况下，滚动到最后矫正一下位置，有时候会偏离一点
                if(!isDown && newState == RecyclerView.SCROLL_STATE_IDLE && isLastItem()){
                    recoverLayout()
                }
            Log.i(TAG, "-----------onScrollStateChanged----------- isMoved = $isMoved newState = $newState isDragging = $isDragging isLastItem = ${isLastItem()}")
          }
          override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
              super.onScrolled(recyclerView, dx, dy)
              Log.i(TAG, "-----------onScrolled----------- dy = $dy isDragging = $isDragging isLastItem = ${isLastItem()}")
              if(!isDragging && isLastItem()){
                  childView!!.stopScroll()
              }
          }
        })

    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        original[childView!!.getLeft(), childView!!.getTop(), childView!!.getRight()] = childView!!.getBottom()
    }

    fun setScrollListener(listener: ScrollListener?) {
        mScrollListener = listener
    }

    private fun isLastItem():Boolean{
        //设置什么布局管理器,就获取什么的布局管理器
        val manager = childView!!.layoutManager as LinearLayoutManager
        //获取最后一个完全显示的ItemPosition ,角标值
        val lastCompletelyVisibleItemPos: Int = manager.findLastCompletelyVisibleItemPosition()

        //获取最后一个刚显示显示的ItemPosition ,角标值，只要有一部分显示就算
        val lastVisibleItemPos: Int = lastCompletelyVisibleItemPos + 1
        //所有条目,数量值
        val totalItemCount: Int = manager.itemCount
        Log.i(TAG,"isLastItem lastVisibleItemPos = $lastVisibleItemPos totalItemCount = $totalItemCount")
        // 判断是否滚动到底部
        return lastVisibleItemPos >= totalItemCount - 1
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if(!this.canRebound){
           return super.dispatchTouchEvent(ev)
        }
        val touchYpos = ev.y
        if (touchYpos >= original.bottom || touchYpos <= original.top) {
            if (isMoved) {
                recoverLayout()
            }
            return true
        }

        return when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                startYpos = ev.y
                isDown = true
                val scrollYpos = (ev.y - startYpos).toInt()
                val moveYpos = (ev.y - lastYpos).toInt()
//                val pullDown = scrollYpos > 0 && canPullDown()
                val pullUp = scrollYpos < 0 && isLastItem()
                if (pullUp) {
                    cancelChild(ev)
                    val offset = (moveYpos * DAMPING_COEFFICIENT).toInt()
                    Log.i(TAG,"ACTION_DOWN offset = $offset")
                    val absOffset = Math.abs(offset)
                    childView!!.scrollBy(0, absOffset)
                    mScrollListener?.onPull(absOffset)
                    isMoved = true
                    isSuccess = false
                    true
                } else {
                    startYpos = ev.y
                    isMoved = false
                    isSuccess = true
                    super.dispatchTouchEvent(ev)
                }
            }
            MotionEvent.ACTION_MOVE -> {
                val scrollYpos = (ev.y - startYpos).toInt()
                val moveYpos = (ev.y - lastYpos).toInt()
                lastYpos = ev.y
//                val pullDown = scrollYpos > 0 && canPullDown()
                val pullUp = scrollYpos < 0 && isLastItem()
                Log.i(TAG,"ACTION_MOVE scrollYpos = $scrollYpos isLastItem = ${isLastItem()}")
                if (pullUp) {
                    cancelChild(ev)
                    val offset = (moveYpos * DAMPING_COEFFICIENT).toInt()
                    Log.i(TAG,"ACTION_MOVE offset = $offset")
                    val absOffset = Math.abs(offset)
                    childView!!.scrollBy(0, absOffset)
                    mScrollListener?.onPull(absOffset)
                    isMoved = true
                    isSuccess = false
                    true
                } else {
                    startYpos = ev.y
                    isMoved = false
                    isSuccess = true
                    super.dispatchTouchEvent(ev)
                }
            }
            MotionEvent.ACTION_UP -> {
                if (isMoved) {
                    recoverLayout()
                    val scrollYpos = (ev.y - startYpos).toInt()
                    val offset = (scrollYpos * DAMPING_COEFFICIENT).toInt()
                    mScrollListener?.onRecover(offset)
                }
                Log.i(TAG,"ACTION_UP isMoved = $isMoved")
                lastYpos = ev.y
                isDown = false
                !isSuccess || super.dispatchTouchEvent(ev)
            }
            else -> true
        }
    }

    /**
     * 取消子view已经处理的事件
     *
     * @param ev event
     */
    private fun cancelChild(ev: MotionEvent) {
        ev.action = MotionEvent.ACTION_CANCEL
        super.dispatchTouchEvent(ev)
    }

    /**
     * 位置还原
     */
    private fun recoverLayout() {
        val manager = childView!!.layoutManager as LinearLayoutManager
        val height = childView!!.getBottom() - childView!!.getTop()
        val offset = height - 104.dp
        Log.i(TAG,"recoverLayout offset = $offset")
        manager.scrollToPositionWithOffset(manager.itemCount - 2,offset)
        isMoved = false
    }

    /**
     * 设置知否可以回弹
     */
    fun setCanRebound(canRebound:Boolean){
        this.canRebound = canRebound
    }


    interface ScrollListener {
        /**
         * 拉动事件回调
         */
        fun onPull(offset:Int)

        /**
         * 回弹
         */
        fun onRecover(offset:Int)

        /**
         * canRebound：是否有关闭阻尼拖动
         */
        fun onScroll(ev: MotionEvent, isScroll:Boolean, isTop:Boolean)

    }

    companion object {
        private const val ANIM_TIME = 300

        /**
         * 阻尼系数
         */
        const val DAMPING_COEFFICIENT = 0.3f
        const val TAG = "DampingScrollLayout"
    }
}