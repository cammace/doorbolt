package com.cammace.doorbolt.di

import com.cammace.doorbolt.ui.StoreListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class StoreListModule {

  @ContributesAndroidInjector
  abstract fun contributeStoreList(): StoreListActivity
}