package app.utility.weatherassist.Composable

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.utility.weatherassist.Composable.Components.CurrentLocationMarker
import app.utility.weatherassist.Composable.Components.TrailingIcon
import app.utility.weatherassist.WeatherViewmodel.WeatherViewModel

@OptIn(ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainCardTest(
    viewModel: WeatherViewModel
) {
    val weatherUiState by viewModel.uiStateWeather.collectAsState()
    val pagerState = rememberPagerState(initialPage = 0)
    val forecastDay by remember { derivedStateOf { weatherUiState.weatherInformation?.forecast?.forecastday } }
    val currentPage by remember { derivedStateOf { pagerState.currentPage } }
    val animateHeight by animateFloatAsState(
        targetValue = if(weatherUiState.cardExtended) 1f else 0.7f,
        tween(800)
    )
    val animateShape by animateDpAsState(
        targetValue = if(weatherUiState.cardExtended) 0.dp else 80.dp,
        tween(800)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.TopCenter
    ){

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            HorizontalPager(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(animateHeight),
                state = pagerState,
                pageCount = forecastDay?.size ?: 0,
                verticalAlignment = Alignment.CenterVertically,
                pageSpacing = 40.dp,
            ) { currentPage ->
                forecastDay?.let { forecastDay ->
                    MainWeatherView(
                        forecastDay = forecastDay,
                        currentPage = currentPage,
                        viewModel = viewModel,
                        onClick = { viewModel.cardExtended(!weatherUiState.cardExtended) },
                        roundedCorner = animateShape,
                        showExtended = weatherUiState.cardExtended
                    )
                }
            }

            TrailingIcon(pagerState = pagerState)

            forecastDay?.let { weeksForecast ->
                AllDayWeatherBar(
                    forecastDay = weeksForecast,
                    currentPage = currentPage,
                    viewModel = viewModel
                )
            }
        }

        AnimatedVisibility(
            visible = weatherUiState.cardExtended,
            enter = slideInVertically(initialOffsetY = { initialOffset -> initialOffset }),
            exit = slideOutVertically(targetOffsetY = { targetOffset -> targetOffset })
        ){
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.statusBars)
                    .padding(20.dp),
                contentAlignment = Alignment.BottomCenter
            ){
               TrailingIcon(pagerState = pagerState)
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.statusBars)
                .padding(20.dp),
            contentAlignment = Alignment.TopCenter
        ){
            weatherUiState.weatherInformation?.location?.name?.let { location ->
                CurrentLocationMarker(location = location)
            }
        }
    }
}