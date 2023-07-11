package app.utility.weatherassist.Data.RequestModel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Forecast(
    val forecastday: List<ForecastDay>
)

@Serializable
data class ForecastDay(
    val date: String,
    @SerialName("date_epoch")
    val dateEpoch: Long,
    val day: Day,
    val astro: Astro,
    val hour: List<Hour>
)

@Serializable
data class Day(
    @SerialName("maxtemp_c")
    val maxTempC: Double,
    @SerialName("maxtemp_f")
    val maxTempF: Double,
    @SerialName("mintemp_c")
    val minTempC: Double,
    @SerialName("mintemp_f")
    val minTempF: Double,
    @SerialName("avgtemp_c")
    val avgTempC: Double,
    @SerialName("avgtemp_f")
    val avgTempF: Double,
    @SerialName("maxwind_mph")
    val maxWindMph: Double,
    @SerialName("maxwind_kph")
    val maxWindKph: Double,
    @SerialName("totalprecip_mm")
    val totalPrecipMm: Double,
    @SerialName("totalprecip_in")
    val totalPrecipIn: Double,
    @SerialName("totalsnow_cm")
    val totalSnowCm: Double,
    @SerialName("avgvis_km")
    val avgVisKm: Double,
    @SerialName("avgvis_miles")
    val avgVisMiles: Double,
    @SerialName("avghumidity")
    val avgHumidity: Double,
    @SerialName("daily_will_it_rain")
    val dailyWillItRain: Int,
    @SerialName("daily_chance_of_rain")
    val dailyChanceOfRain: Int,
    @SerialName("daily_will_it_snow")
    val dailyWillItSnow: Int,
    @SerialName("daily_chance_of_snow")
    val dailyChanceOfSnow: Int,
    val condition: Condition,
    val uv: Double
)

@Serializable
data class Astro(
    val sunrise: String,
    val sunset: String,
    val moonrise: String,
    val moonset: String,
    @SerialName("moon_phase")
    val moonPhase: String,
    @SerialName("moon_illumination")
    val moonIllumination: String,
    @SerialName("is_moon_up")
    val isMoonUp: Int,
    @SerialName("is_sun_up")
    val isSunUp: Int
)

@Serializable
data class Hour(
    @SerialName("time_epoch")
    val timeEpoch: Long,
    val time: String,
    @SerialName("temp_c")
    val tempC: Double,
    @SerialName("temp_f")
    val tempF: Double,
    @SerialName("is_day")
    val isDay: Int,
    val condition: Condition,
    @SerialName("wind_mph")
    val windMph: Double,
    @SerialName("wind_kph")
    val windKph: Double,
    @SerialName("wind_degree")
    val windDegree: Int,
    @SerialName("wind_dir")
    val windDir: String,
    @SerialName("pressure_mb")
    val pressureMb: Double,
    @SerialName("pressure_in")
    val pressureIn: Double,
    @SerialName("precip_mm")
    val precipMm: Double,
    @SerialName("precip_in")
    val precipIn: Double,
    val humidity: Int,
    val cloud: Int,
    @SerialName("feelslike_c")
    val feelsLikeC: Double,
    @SerialName("feelslike_f")
    val feelsLikeF: Double,
    @SerialName("windchill_c")
    val windChillC: Double,
    @SerialName("windchill_f")
    val windChillF: Double,
    @SerialName("heatindex_c")
    val heatIndexC: Double,
    @SerialName("heatindex_f")
    val heatIndexF: Double,
    @SerialName("dewpoint_c")
    val dewPointC: Double,
    @SerialName("dewpoint_f")
    val dewPointF: Double,
    @SerialName("will_it_rain")
    val willItRain: Int,
    @SerialName("chance_of_rain")
    val chanceOfRain: Int,
    @SerialName("will_it_snow")
    val willItSnow: Int,
    @SerialName("chance_of_snow")
    val chanceOfSnow: Int,
    @SerialName("vis_km")
    val visKm: Double,
    @SerialName("vis_miles")
    val visMiles: Double,
    @SerialName("gust_mph")
    val gustMph: Double,
    @SerialName("gust_kph")
    val gustKph: Double,
    val uv: Double
)


@Serializable
data class Condition(
    val text: String,
    val icon: String,
    val code: Int
)
@Serializable
data class Location(
    val name: String,
    val region: String,
    val country: String,
    val lat: Double,
    val lon: Double,
    val tz_id: String,
    @SerialName("localtime_epoch")
    val localtimeEpoch: Long,
    val localtime: String
)
@Serializable
data class Current(
    @SerialName("last_updated_epoch")
    val lastUpdatedEpoch: Long,
    @SerialName("last_updated")
    val lastUpdated: String,
    @SerialName("temp_c")
    val tempC: Double,
    @SerialName("temp_f")
    val tempF: Double,
    @SerialName("is_day")
    val isDay: Int,
    val condition: Condition,
    @SerialName("wind_mph")
    val windMph: Double,
    @SerialName("wind_kph")
    val windKph: Double,
    @SerialName("wind_degree")
    val windDegree: Int,
    @SerialName("wind_dir")
    val windDir: String,
    @SerialName("pressure_mb")
    val pressureMb: Double,
    @SerialName("pressure_in")
    val pressureIn: Double,
    @SerialName("precip_mm")
    val precipMm: Double,
    @SerialName("precip_in")
    val precipIn: Double,
    val humidity: Int,
    val cloud: Int,
    @SerialName("feelslike_c")
    val feelsLikeC: Double,
    @SerialName("feelslike_f")
    val feelsLikeF: Double,
    @SerialName("vis_km")
    val visKm: Double,
    @SerialName("vis_miles")
    val visMiles: Double,
    val uv: Double,
    @SerialName("gust_mph")
    val gustMph: Double,
    @SerialName("gust_kph")
    val gustKph: Double
)

@Serializable
data class WeatherData(
    val location: Location? = null,
    val current: Current? = null,
    val forecast: Forecast? = null
)

