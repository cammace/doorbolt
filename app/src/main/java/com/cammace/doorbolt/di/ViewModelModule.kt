package com.cammace.doorbolt.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cammace.doorbolt.viewmodel.StoreListViewModel
import com.cammace.doorbolt.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
@Suppress("unused")
abstract class ViewModelModule {

  @Binds
  @IntoMap
  @ViewModelKey(StoreListViewModel::class)
  abstract fun bindStoreListViewModel(storeListViewModel: StoreListViewModel): ViewModel

  @Binds
  abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}