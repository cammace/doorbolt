package com.cammace.doorbolt.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.cammace.doorbolt.api.DoorDashService
import com.cammace.doorbolt.model.Restaurant
import com.cammace.doorbolt.util.RxImmediateSchedulerRule
import io.reactivex.Observable
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response


@RunWith(MockitoJUnitRunner::class)
class StoreListViewModelTest {

  // Replaces the Android Architecture background executor
  // to one that performs synchronously, making testing easier.
  @Rule
  @JvmField
  var rule = InstantTaskExecutorRule()

  // Test rule for making the RxJava to run synchronously in unit test
  companion object {
    @ClassRule
    @JvmField
    val schedulers = RxImmediateSchedulerRule()

    @ClassRule
    @JvmField
    val thrown = ExpectedException.none()
  }

  @Mock
  lateinit var service: DoorDashService

  @Mock
  lateinit var observer: Observer<List<Restaurant>?>

  lateinit var viewModel: StoreListViewModel

  @Before
  fun setUp() {
    viewModel = StoreListViewModel(service)
  }

  @Test
  fun loadRestaurants_doesTriggerObservableWithCorrectData() {
    val body = listOf(
      Restaurant(
        1L,
        "name",
        "description",
        "coverImg",
        "status",
        1.0))

    `when`(service.getRestaurants(DOORDASH_HQ_LATITUDE, DOORDASH_HQ_LONGITUDE, OFFSET, LIMIT))
      .thenReturn(Observable.just(Response.success(body)))

    // observe the retrofit response
    viewModel.loadRestaurants().observeForever(observer)
    verify(observer).onChanged(body)
  }

  @Test
  fun loadRestaurants_doesReturnNullWhenBodyIsNull() {
    val body: List<Restaurant>? = null
    `when`(service.getRestaurants(DOORDASH_HQ_LATITUDE, DOORDASH_HQ_LONGITUDE, OFFSET, LIMIT))
      .thenReturn(Observable.just(Response.success(body)))

    // observe the retrofit response
    viewModel.loadRestaurants().observeForever(observer)
    verify(observer).onChanged(null)
  }
}