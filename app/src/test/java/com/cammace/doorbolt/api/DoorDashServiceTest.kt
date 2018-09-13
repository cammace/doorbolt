package com.cammace.doorbolt.api

import com.cammace.doorbolt.model.Restaurant
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.observers.TestObserver
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.StandardCharsets.UTF_8
import java.util.*

const val RESTAURANT_FIXTURE = "restaurant.json"

class DoorDashServiceTest {

  private lateinit var service: DoorDashService
  private val mockWebServer: MockWebServer = MockWebServer()


  @Before
  fun setUp() {
    // Have mock web server return fixture when requested
    mockWebServer.setDispatcher(object : Dispatcher() {
      override fun dispatch(request: RecordedRequest?): MockResponse {
        return MockResponse().setBody(loadJsonFixture(RESTAURANT_FIXTURE))
      }
    })
    mockWebServer.start()

    service = Retrofit.Builder()
      .baseUrl(mockWebServer.url(""))
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .build()
      .create(DoorDashService::class.java)
  }

  @After
  fun teardown() {
    mockWebServer.shutdown()
  }

  @Test
  fun sanity() {
    val testObservable = TestObserver<Response<List<Restaurant>>>()
    service.getRestaurants(1.23456, 2.34567, 1.0, 5)
      .subscribe(testObservable)
    testObservable.assertNoErrors()
    val response = testObservable.values()[0]
    assertThat(response.code(), `is`(200))
  }

  @Test
  fun response_doesConformToRestaurantModelCorrectly() {
    val testObservable = TestObserver<Response<List<Restaurant>>>()
    service.getRestaurants(1.23456, 2.34567, 1.0, 5)
      .subscribe(testObservable)
    testObservable.assertNoErrors()
    val response = testObservable.values()[0]

    val firstRestaurant = response.body()?.get(0)
      ?: throw NullPointerException("response body failed to be populated with fixture.")
    assertThat(firstRestaurant.id, `is`(36L))
    assertThat(firstRestaurant.coverImgUrl, `is`("https://cdn.doordash.com/media/restaurant/cover/Agave-Mexican-Bistro.png"))
    assertThat(firstRestaurant.deliveryFee, `is`(0.0))
    assertThat(firstRestaurant.description, `is`("Traditional Mexican Meets Contemporary California"))
    assertThat(firstRestaurant.status, `is`("43 mins"))
    assertThat(firstRestaurant.name, `is`("Agave Mexican Bistro"))
  }

  companion object {
    fun loadJsonFixture(filename: String): String {
      val classLoader = javaClass.classLoader
      val inputStream = classLoader!!.getResourceAsStream(filename)
      val scanner = Scanner(inputStream, UTF_8.name()).useDelimiter("\\A")
      return if (scanner.hasNext()) scanner.next() else ""
    }
  }
}