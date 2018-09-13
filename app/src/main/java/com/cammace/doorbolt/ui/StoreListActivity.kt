package com.cammace.doorbolt.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.cammace.doorbolt.R
import com.cammace.doorbolt.viewmodel.StoreListViewModel
import dagger.android.support.DaggerAppCompatActivity
import timber.log.Timber
import javax.inject.Inject

class StoreListActivity : DaggerAppCompatActivity() {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory
  private lateinit var viewModel: StoreListViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_storelist)

    viewModel = ViewModelProviders.of(this, viewModelFactory).get(StoreListViewModel::class.java)
    viewModel.loadRestaurants().observe(this, Observer { listOfRestaurants ->
      if (listOfRestaurants != null) {
        Timber.v(listOfRestaurants[0].name)
      }
    })
  }
}
