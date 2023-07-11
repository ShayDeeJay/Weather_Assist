package app.utility.weatherassist.Composable

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.utility.weatherassist.Data.RequestModel.ForecastDay
import app.utility.weatherassist.WeatherViewmodel.WeatherViewModel
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun AllDayWeatherBar(
    forecastDay: List<ForecastDay>,
    currentPage: Int,
    viewModel: WeatherViewModel,
){
    val currentTime by remember { derivedStateOf { LocalDateTime.now().hour } }
    val pagerState = rememberPagerState(initialPage = currentTime)

    HorizontalPager(
        modifier = Modifier.padding(bottom = 15.dp),
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 130.dp),
        verticalAlignment = Alignment.CenterVertically,
        pageCount = forecastDay[currentPage].hour.size,
    ) { day ->

        val isCurrentDay by remember {
            derivedStateOf { currentTime == day }
        }

        val animateFocusedWidth by animateDpAsState(
            targetValue = if(isCurrentDay) 130.dp else 110.dp
        )

        val animateFocusedHeight by animateDpAsState(
            targetValue = if(isCurrentDay) 150.dp else 130.dp
        )

        val animateColor by animateColorAsState(
            targetValue = if(isCurrentDay)
                MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.background
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .width(animateFocusedWidth)
                    .height(animateFocusedHeight),
                colors = CardDefaults.cardColors(animateColor),
                shape = RoundedCornerShape(30.dp),
                elevation = CardDefaults.cardElevation(5.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${forecastDay[currentPage].hour[day].tempC.toInt()}°",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Icon(
                        modifier = Modifier
                            .weight(1f)
                            .size(30.dp),
                        imageVector = ImageVector.vectorResource(
                            id = viewModel.showWeathersIcon(
                                forecastDay[currentPage].hour[day].condition.code,
                                forecastDay[currentPage].hour[day].isDay
                            )
                        ),
                        contentDescription = ""
                    )

                    Text(
                        text = viewModel.showCurrentHour(forecastDay[currentPage].hour[day].time),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}