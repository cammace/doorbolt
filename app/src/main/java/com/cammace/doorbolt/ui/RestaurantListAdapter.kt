package com.cammace.doorbolt.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.cammace.doorbolt.R
import com.cammace.doorbolt.databinding.ItemRestaurantBinding
import com.cammace.doorbolt.model.Restaurant

class RestaurantListAdapter: RecyclerView.Adapter<RestaurantListAdapter.RestaurantViewHolder>() {

  private var restaurantList = listOf<Restaurant>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
    val binding: ItemRestaurantBinding
      = DataBindingUtil.inflate(layoutInflater, R.layout.item_restaurant, parent, false)

    return RestaurantViewHolder(binding)
  }

  override fun getItemCount(): Int {
    return restaurantList.size
  }

  override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
    holder.binding.restaurant = restaurantList[position]
    holder.binding.executePendingBindings()
  }

  fun setRestaurantList(restaurantList: List<Restaurant>) {
    this.restaurantList = restaurantList
    notifyDataSetChanged()
  }

  class RestaurantViewHolder(val binding: ItemRestaurantBinding): RecyclerView.ViewHolder(binding.root)
}