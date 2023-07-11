package app.utility.weatherassist.Data.ApiServices

import app.utility.weatherassist.Data.RequestModel.WeatherData
import retrofit2.http.GET
import retrofit2.http.Query

private const val key = "?key=44559791764a404ab05151723230807"
//http://api.weatherapi.com/v1/current.json?key=44559791764a404ab05151723230807&q=London&aqi=no

interface WeatherApi{

    @GET("forecast.json$key")
    suspend fun getWeatherData(
        @Query("q")location: String,
        @Query("days")days: Int = 7,
        @Query("aqi")aqi: String = "no",
        @Query("alerts")alerts: String = "no"
    ): WeatherData

}
