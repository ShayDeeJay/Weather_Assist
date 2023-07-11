package app.utility.weatherassist.Data.Implementations

import app.utility.weatherassist.Data.ApiServices.WeatherApi
import app.utility.weatherassist.Data.Interfaces.WeatherRepository
import app.utility.weatherassist.Data.RequestModel.WeatherData

class RepositoryImpl(
    private val api: WeatherApi,
): WeatherRepository {

    override suspend fun getWeatherCall(location: String): WeatherData {
        return api.getWeatherData(location)
    }

}