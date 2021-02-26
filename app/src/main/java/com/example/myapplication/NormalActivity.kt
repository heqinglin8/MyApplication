/*
 * Copyright (c) 2016-present. Drakeet Xu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.MultiTypeAdapter
import com.example.myapplication.listview.*
import com.example.myapplication.widget.DampingScrollLayout
import java.util.*

/**
 * @author Drakeet Xu
 */
class NormalActivity : AppCompatActivity() {

  private val adapter = MultiTypeAdapter()
  private val items = ArrayList<Any>()

  companion object{
    const val TAG = "NormalActivity"
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_list)
    val overScrollLayout = findViewById<DampingScrollLayout>(R.id.over_scroll_layout)
    val recyclerView = findViewById<RecyclerView>(R.id.list)

    adapter.register(TextItemViewBinder())
    adapter.register(ImageItemViewBinder())
    // ✨✨✨
    adapter.register(RichViewDelegate())
    adapter.register(FooterItemViewBinder())
    recyclerView.adapter = adapter

    val textItem = TextItem("world")
    val imageItem = ImageItem(R.mipmap.ic_launcher)
    val richItem = RichItem("小艾大人赛高", R.mipmap.img_11)

//    for (i in 0..19) {
//      richItem.text = richItem.text + i
//      items.add(textItem)
//      items.add(imageItem)
//      richItem.text = richItem.text + i
//      items.add(richItem)
//    }


    for (i in 0..19) {
      if(i%2 ==0){
        items.add(TextItem("world pos = $i"))
      }else{
        items.add(RichItem("小艾大人赛高 pos = $i", R.mipmap.img_11))
      }
    }
    items.add(FooterItem("加载更多"))
    adapter.items = items
    adapter.notifyDataSetChanged()

    overScrollLayout.setScrollListener(object:DampingScrollLayout.ScrollListener{
      override fun onPull(offset: Int) {
        Log.i(TAG, "-----------onPull-----------offset = $offset")
        if (offset > 3) {
          val itemView: View? = recyclerView.layoutManager!!.findViewByPosition(recyclerView.layoutManager!!.itemCount -1)
          Log.i(TAG, "-----------onPull----------- itemView = $itemView")
          if (itemView != null) {
            val tv = itemView.findViewById<View>(R.id.text)
            Log.i(TAG, "-----------onPull----------- tv = $tv")
            if (tv is TextView) {
              tv.text = "放开进入礼物墙 offset=$offset"
            }
          }
        }
      }
      override fun onRecover(offset: Int) {

      }

      override fun onScroll(ev: MotionEvent, isScroll: Boolean, isTop: Boolean) {

      }

    })

//    recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//      override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//        super.onScrollStateChanged(recyclerView, newState)
//        if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
//
//        }
//      }
//      override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//        super.onScrolled(recyclerView, dx, dy)
//        Log.i(TAG, "-----------onScrolled----------- dy = $dy")
//        if(dy > 10){
////          val itemHolder = recyclerView.findViewHolderForItemId(1)
//            val itemView: View? = recyclerView.layoutManager!!.findViewByPosition(6)
//            Log.i(TAG, "-----------onScrolled----------- itemView = $itemView")
//            if(itemView != null){
//                val tv = itemView.findViewById<View>(R.id.text)
//              Log.i(TAG, "-----------onScrolled----------- tv = $tv")
//              if(tv is TextView){
//                tv.text  = "dy = $dy"
//              }
//            }
//        }
//      }
//    })
  }
}
