package com.cammace.doorbolt.model

import com.google.gson.annotations.SerializedName

data class Restaurant(
  val id: Long,
  val name: String,
  val description: String,
  @SerializedName("cover_img_url") val coverImgUrl: String,
  val status: String,
  @SerializedName("delivery_fee") val deliveryFee: Double?
  )