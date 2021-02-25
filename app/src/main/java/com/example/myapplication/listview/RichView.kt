package com.example.myapplication.listview

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.example.myapplication.R

/**
 * @author Drakeet Xu
 */
class RichView(context: Context) : LinearLayout(context) {

  val textView = AppCompatTextView(context).apply {
    setTextColor(Color.BLACK)
    this.id = R.id.text
    addView(this, LayoutParams(WRAP_CONTENT, WRAP_CONTENT))
  }

  val imageView = AppCompatImageView(context).apply {
    addView(this, LayoutParams(72.dp, 72.dp))
  }


  init {
    orientation = VERTICAL
    gravity = Gravity.CENTER
    setPadding(16.dp, 16.dp, 16.dp, 16.dp)
    setBackgroundColor(Color.parseColor("#ff0000"))
  }
}
