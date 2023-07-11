package app.utility.weatherassist.UiState

import app.utility.weatherassist.Data.RequestModel.WeatherData

data class WeatherUiState(
    val weatherInformation: WeatherData? = null,
    val location: String = "London",
    val responseCode: Int? = null,
    val cardExtended: Boolean = false
)