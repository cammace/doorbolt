package com.cammace.doorbolt

import com.cammace.doorbolt.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

import timber.log.Timber

class DoorBoltApplication : DaggerApplication() {

  override fun onCreate() {
    super.onCreate()

    // Initialize Timber if in debug mode
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }
  }

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    val appComponent = DaggerAppComponent.builder().application(this).build()
    appComponent.inject(this)
    return appComponent
  }
}