
# V-forecast

Weather service that utilizes [OpenWeatherMap API](https://openweathermap.org/api) for fetching temperature measurements to use for calculating average temperatures for cities.


## Requirements

- JDK 17
- Maven
## Technologies

- Spring Boot V3.1.4
- Maven
- H2 Database
- Flyway
## How to Run

There are several ways to run the application on your machine, locally. One way is to execute the main method inside the ```example.vforecast.VForecastApplication``` class from your preferred IDE.

Alternatively if you have [Maven](https://maven.apache.org/) installed you can run this command inside the root directory.

```
mvn spring-boot:run
```

The application will start on:

```
http://localhost:8080/api
```
## About the Service

The service is a simple REST API that uses data gathered from the [OpenWeatherMap API](https://openweathermap.org/api) to calculate average temperatures. It uses an in-memory database (H2) to store the data. You can access the database on the following url ```http://localhost:8080/api/h2-console```.

When you open up the url you will see a prompt to login:


Make sure that the console fields match the ones on the picture and then press connect to access the database. If you are successful you will be greeted with this screen:

## API

You can view the Swagger OpenAPI 3.0 docs (springdoc) by accessing
```
http://localhost:8080/api/swagger-ui/index.html
```

Here are the endpoints you can call:

### (GET Method) Find all available cities
Find all of the available cities stored in the database for which you can calculate an average temperature

```
http://localhost:8080/api/cities
```

### (GET Method) Find average temperature of cities
Finds the average temperature for every city. Must have a time period specified. The time period must be inside the range of the next 5 days. The reason is because the temperature measurements that are used are gathered from the [5 day forecast](https://openweathermap.org/forecast5) from OpenWeatherMap. Example:

```
http://localhost:8080/api/cities/average-temperature?from={from}&to={to}
```

Parameters:

``` from ``` - **required** - Start of the time period for which we calculate the average temperature of a city. It must be in the scope of the next 5 days. Use the format: ``` yyyy-MM-dd'T'HH:mm ```. For example: ```2023-10-12T06:00```

``` to ``` - **required** - End of the time period for which we calculate the average temperature of a city. It must be in the scope of the next 5 days. Use the format: ``` yyyy-MM-dd'T'HH:mm ```. For example: ```2023-10-12T06:00```

``` cities ```- *optional* - Cities for which we want to calculate the average temperature. If left empty it calculates the average temperature for every city. To enter the names of multiple cities use a comma for separation. For example: 
```
http://localhost:8080/api/cities/average-temperature?from=2023-10-11T00:00&to=2023-10-12T00:00&cities=Subotica,Beograd
```

``` sort ``` - *optional* - Type of sort. Use if you want to sort returned cities by average temperature. Use ```asc``` for ascending and ```desc``` for descending sort. For example:
```
http://localhost:8080/api/cities/average-temperature?from=2023-10-11T00:00&to=2023-10-12T00:00&cities=Subotica,Beograd&sort=asc
```
## How to use different a unit system

The service uses the metric system for the temperatures as its default. To change the unit system go to ```application.yml``` and change the ```open-weather-map.units``` variable to desired unit system. The supported unit systems depend on the [5 day forecast](https://openweathermap.org/forecast5) endpoint provided by OpenWeatherMap.
## Authors

- [@stefanpekez](https://github.com/stefanpekez)

