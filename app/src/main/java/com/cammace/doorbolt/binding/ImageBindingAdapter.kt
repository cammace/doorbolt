package com.cammace.doorbolt.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

object ImageBindingAdapter {

  @JvmStatic
  @BindingAdapter("imageUrl")
  fun setImageUrl(view: ImageView, coverImgUrl: String) {
    Picasso.get().load(coverImgUrl).into(view)
  }
}