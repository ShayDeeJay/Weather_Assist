package app.utility.weatherassist.Data.Di

import app.utility.weatherassist.Data.ApiServices.WeatherApi
import app.utility.weatherassist.Data.Implementations.RepositoryImpl
import app.utility.weatherassist.Data.Interfaces.WeatherRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseObject {

    private val json = Json { ignoreUnknownKeys = true }
    private const val BASE_URL = "https://api.weatherapi.com/v1/"

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun providesApi(): WeatherApi {
        return Retrofit.Builder()
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(BASE_URL)
            .build()
            .create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun providesRepository(api: WeatherApi): WeatherRepository = RepositoryImpl(api)

}