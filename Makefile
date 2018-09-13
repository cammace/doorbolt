# easily create the test fixtures
restaurant-fixture:
	curl "https://api.doordash.com/v2/restaurant/?lat=37.422740&lng=-122.139956&offset=0&limit=50" \
		-o app/src/test/resources/restaurant.json
