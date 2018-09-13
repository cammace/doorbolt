package com.cammace.doorbolt.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cammace.doorbolt.model.Restaurant
import com.cammace.doorbolt.repo.RestaurantRepository
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

class StoreListViewModel @Inject constructor(restaurantRepository: RestaurantRepository) : ViewModel() {

  private val restaurantsLiveData = restaurantRepository.loadRestaurants()
  val restaurants: LiveData<List<Restaurant>> get() = restaurantsLiveData
}