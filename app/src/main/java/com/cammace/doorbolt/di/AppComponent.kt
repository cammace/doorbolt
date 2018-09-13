package com.cammace.doorbolt.di

import android.app.Application
import com.cammace.doorbolt.DoorBoltApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, StoreListModule::class])
interface AppComponent : AndroidInjector<DaggerApplication> {

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(application: Application): Builder

    fun build(): AppComponent
  }

  fun inject(doorBoltApplication: DoorBoltApplication)

  override fun inject(instance: DaggerApplication?)
}