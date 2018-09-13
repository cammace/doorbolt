package com.cammace.doorbolt.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cammace.doorbolt.api.DoorDashService
import com.cammace.doorbolt.model.Restaurant
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

const val DOORDASH_HQ_LATITUDE = 37.422740
const val DOORDASH_HQ_LONGITUDE = -122.139956
const val OFFSET = 0.0
const val LIMIT = 50

class StoreListViewModel @Inject constructor(private val doorDashService: DoorDashService) : ViewModel() {

  private val compositeDisposable = CompositeDisposable()

  fun loadRestaurants(): LiveData<List<Restaurant>?> {
    val restaurantListLiveData = MutableLiveData<List<Restaurant>?>()
    val disposable = doorDashService
      .getRestaurants(DOORDASH_HQ_LATITUDE, DOORDASH_HQ_LONGITUDE, OFFSET, LIMIT)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeWith(object : DisposableObserver<Response<List<Restaurant>>>() {
        override fun onNext(response: Response<List<Restaurant>>) {
          checkResponseSuccess(response)
          restaurantListLiveData.value = response.body()
        }

        override fun onComplete() {
          // no-op
        }

        override fun onError(throwable: Throwable) {
          // Pass null so we can display an error message to the user
          restaurantListLiveData.value = null
          throwable.printStackTrace()
        }
      })
    compositeDisposable.add(disposable)
    return restaurantListLiveData
  }

  private fun checkResponseSuccess(response: Response<*>) {
    if (!response.isSuccessful) {
      val message = response.errorBody()?.string()
      val errorMessage = if (message.isNullOrEmpty()) {
        response.message()
      } else {
        message
      }
      throw RuntimeException(errorMessage)
    }
  }

  /**
   * Called when the activity this view models attached to is destroyed.
   */
  override fun onCleared() {
    compositeDisposable.dispose()
    super.onCleared()
  }
}