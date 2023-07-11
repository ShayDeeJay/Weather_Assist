package app.utility.weatherassist.Data.Interfaces

import app.utility.weatherassist.Data.RequestModel.WeatherData

interface WeatherRepository {
    suspend fun getWeatherCall(location: String): WeatherData
}
