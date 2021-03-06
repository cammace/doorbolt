package com.cammace.doorbolt.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.cammace.doorbolt.R
import com.cammace.doorbolt.databinding.ItemRestaurantBinding
import com.cammace.doorbolt.model.Restaurant
import com.cammace.doorbolt.viewmodel.StoreListViewModel
import kotlinx.android.synthetic.main.item_restaurant.view.*
import timber.log.Timber

class RestaurantListAdapter(private val viewModel: StoreListViewModel)
  : RecyclerView.Adapter<RestaurantListAdapter.RestaurantViewHolder>() {

  private var restaurantList = listOf<Restaurant>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
    val binding: ItemRestaurantBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_restaurant, parent, false)
    return RestaurantViewHolder(binding, viewModel)
  }

  override fun getItemCount(): Int {
    return restaurantList.size
  }

  override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
    holder.binding.favorite = viewModel.isRestaurantFavorite(restaurantList[position])
    holder.binding.restaurant = restaurantList[position]
    holder.binding.executePendingBindings()

    holder.binding.root.imageButton_restaurantItem_favorite.setOnClickListener {
      holder.favoriteItemClicked(restaurantList[position])
    }
  }

  fun setRestaurantList(restaurantList: List<Restaurant>) {
    this.restaurantList = restaurantList
    notifyDataSetChanged()
  }

  class RestaurantViewHolder(val binding: ItemRestaurantBinding, private val viewModel: StoreListViewModel)
    : RecyclerView.ViewHolder(binding.root) {

    fun favoriteItemClicked(restaurant: Restaurant) {
      Timber.v("favorite clicked.")
      binding.favorite = viewModel.updatePreferences(restaurant)
      binding.executePendingBindings()
    }

  }
}