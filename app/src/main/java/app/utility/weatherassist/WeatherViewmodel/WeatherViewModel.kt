package app.utility.weatherassist.WeatherViewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.utility.weatherassist.Data.IconHandler.WeatherIconDisplay
import app.utility.weatherassist.Data.Interfaces.WeatherRepository
import app.utility.weatherassist.Data.RequestModel.WeatherData
import app.utility.weatherassist.Data.Utilities.DateConverter
import app.utility.weatherassist.UiState.WeatherUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
): ViewModel() {

    private val weatherUiStateFlow = MutableStateFlow(WeatherUiState())
    val uiStateWeather: StateFlow<WeatherUiState> = weatherUiStateFlow.asStateFlow()
    private val dateConverter = DateConverter()
    private val weatherIconDisplay = WeatherIconDisplay()

    init {
        getWeather()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getWeather(){
        viewModelScope.launch {
            try {
                val result = repository.getWeatherCall(
                    location = uiStateWeather.value.location
                )
                updateIncomingDataResults(result = result)
            } catch (e: IOException){
                println("IO Exception")
            } catch (e: HttpException) {
                val response = e.code()
                networkCallResponseCode(response)
            }
        }
    }

    private fun networkCallResponseCode(responseCode: Int) {
        weatherUiStateFlow.update { updateField ->
            updateField.copy(
                responseCode = responseCode
            )
        }
    }

    private fun updateIncomingDataResults(result: WeatherData){
        weatherUiStateFlow.update { currentState ->
            currentState.copy(
                weatherInformation = result
            )
        }
    }

    fun cardExtended(cardExtended: Boolean){
        weatherUiStateFlow.update { currentState ->
            currentState.copy(
                cardExtended = cardExtended
            )
        }
    }

    fun dateConverter(date: String): String {
        return dateConverter.convertDateToDayOfWeek(date)
    }

    fun showWeathersIcon(weatherCode: Int, isDay: Int): Int{
        return weatherIconDisplay.iconDisplay(weatherCode,isDay)
    }

    fun showCurrentHour(time: String): String {
        return dateConverter.convertStringToTime(time)
    }
}