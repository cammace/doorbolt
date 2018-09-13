package com.cammace.doorbolt.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cammace.doorbolt.api.DoorDashService
import com.cammace.doorbolt.model.Restaurant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

const val DOORDASH_HQ_LATITUDE = 37.422740
const val DOORDASH_HQ_LONGITUDE = -122.139956
const val OFFSET = 0.0
const val LIMIT = 50

@Singleton
class RestaurantRepository @Inject constructor(private val doorDashService: DoorDashService) {

  /**
   * Returns a LiveData object that contains null if an error occurs so we can display to the user
   * an error message. Otherwise, the LiveData response will contain the list of restaurants
   */
  fun loadRestaurants(): LiveData<List<Restaurant>> {
    val data = MutableLiveData<List<Restaurant>>()
    doorDashService.getRestaurants(DOORDASH_HQ_LATITUDE, DOORDASH_HQ_LONGITUDE, OFFSET, LIMIT)
      .enqueue(object : Callback<List<Restaurant>> {
        override fun onResponse(call: Call<List<Restaurant>>, response: Response<List<Restaurant>>) {
          // Handle all potential errors which could occur inside the response
          handlePotentialResponseErrors(response)
          data.value = response.body()
        }

        override fun onFailure(call: Call<List<Restaurant>>, throwable: Throwable) {
          Timber.e(throwable, "Error occurred trying to fetch the API data.")
          data.value = null
        }
      })
    return data
  }

  private fun handlePotentialResponseErrors(response: Response<List<Restaurant>>) {
    if (!response.isSuccessful) {
      val message = response.errorBody()?.string()
      if (message.isNullOrEmpty()) {
        Timber.e(response.message())
      } else {
        Timber.e(message)
      }
    } else if (response.body() == null || response.code() == 204) {
      Timber.e("Api response is empty.")
    }
  }
}
