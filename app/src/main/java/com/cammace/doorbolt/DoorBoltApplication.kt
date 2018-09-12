package com.cammace.doorbolt

import android.app.Application
import timber.log.Timber

class DoorBoltApplication : Application() {

  override fun onCreate() {
    super.onCreate()

    // Initialize Timber if in debug mode
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }
  }
}