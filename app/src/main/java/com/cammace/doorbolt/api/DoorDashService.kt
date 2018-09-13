package com.cammace.doorbolt.api

import com.cammace.doorbolt.model.Restaurant
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DoorDashService {

  @GET("/v2/restaurant/")
  fun getRestaurants(
    @Query("lat") latitude: Double,
    @Query("lng") longitude: Double,
    @Query("offset") offset: Double,
    @Query("limit") limit: Int): Call<List<Restaurant>>

}