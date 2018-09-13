package com.cammace.doorbolt.model

data class Restaurant(
  val id: Long,
  val name: String,
  val description: String,
  val coverImgUrl: String,
  val status: String,
  val deliveryFee: Double?
  )