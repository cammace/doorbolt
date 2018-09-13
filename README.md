# DoorBolt
DoorBolt is an Android app which populates a list with restaurants near the DoorDash headquarters.

## Architecture
DoorBolt is written in Kotlin using a single activity MVVM architecture. The app targets the latest API (28) and supports All API levels going back to API 14. The list provided below list the 3rd party libraries used:

- [Android Bindings library](https://developer.android.com/topic/libraries/data-binding/) to populate the views
- [ViewModel/LiveData](https://developer.android.com/topic/libraries/architecture/viewmodel) Android Architecture Components library to extract logical data from the app's views.
- [Android Jetpack](https://developer.android.com/jetpack/) For backwards compatibility support
- [Constraint Layout](https://developer.android.com/reference/android/support/constraint/ConstraintLayout) for simplifying the view hierarchy
- [Dagger 2](https://github.com/google/dagger) for dependency injection
- [RxJava](https://github.com/ReactiveX/RxJava) for asynchronously calls
- [Retrofit](https://square.github.io/retrofit/) for REST api communication
- [GSON](https://github.com/google/gson) for json deserialization
- [Picasso](http://square.github.io/picasso/) for image loading
- [Timber](https://github.com/JakeWharton/timber) for logging
- [mockito](https://site.mockito.org/) for mocking in tests
- [Okhttp Mockwebserver](https://github.com/square/okhttp/tree/master/mockwebserver) for testing against fixtures

## Generate test fixtures
Run the command `make restaurant-fixture` inside terminal (on Mac) to generate or refresh the fixtures used for testing.