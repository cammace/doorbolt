package com.cammace.doorbolt.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.cammace.doorbolt.R
import com.cammace.doorbolt.databinding.ActivityStorelistBinding
import com.cammace.doorbolt.viewmodel.StoreListViewModel
import dagger.android.support.DaggerAppCompatActivity
import timber.log.Timber
import javax.inject.Inject

class StoreListActivity : DaggerAppCompatActivity() {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory
  private lateinit var viewModel: StoreListViewModel

  private lateinit var binding: ActivityStorelistBinding
  private var adapter: RestaurantListAdapter? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_storelist)
    viewModel = ViewModelProviders.of(this, viewModelFactory).get(StoreListViewModel::class.java)

    initRestaurantList()
    requestRestaurants()
  }

  private fun initRestaurantList() {
    adapter = RestaurantListAdapter()
    binding.recyclerViewStoreListRestaurants.layoutManager = LinearLayoutManager(this)
    binding.recyclerViewStoreListRestaurants.adapter = adapter
  }

  private fun requestRestaurants() {
    viewModel.loadRestaurants().observe(this, Observer { listOfRestaurants ->
      if (listOfRestaurants != null) {
        Timber.v("Successfully received a list of restaurants, passing to adapter now.")
        adapter?.setRestaurantList(listOfRestaurants)
      }
    })
  }
}
