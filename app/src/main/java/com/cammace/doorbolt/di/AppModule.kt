package com.cammace.doorbolt.di

import com.cammace.doorbolt.api.DoorDashService
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val BASE_URL = "https://api.doordash.com/"

@Module(includes = [ViewModelModule::class])
class AppModule {

  @Provides
  @Singleton
  fun provideDoorDashService(): DoorDashService {
    return Retrofit.Builder()
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .baseUrl(BASE_URL)
      .build()
      .create(DoorDashService::class.java)
  }
}