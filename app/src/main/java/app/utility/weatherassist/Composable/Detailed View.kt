package app.utility.weatherassist.Composable

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.utility.weatherassist.Data.RequestModel.ForecastDay
import app.utility.weatherassist.R
import app.utility.weatherassist.WeatherViewmodel.WeatherViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainWeatherView(
    forecastDay: List<ForecastDay>,
    currentPage: Int,
    viewModel: WeatherViewModel,
    onClick:() -> Unit,
    roundedCorner: Dp,
    showExtended: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        shape = RoundedCornerShape(
            bottomEnd = roundedCorner,
            bottomStart = roundedCorner
        ),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onClick() }
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Spacer(modifier = Modifier.height(80.dp))

            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .animateContentSize(tween(500)),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Icon(
                    modifier = Modifier.size(110.dp),
                    imageVector = ImageVector.vectorResource(
                        id = viewModel.showWeathersIcon(forecastDay[currentPage].day.condition.code,1)
                    ),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.Center
                ){
                    Text(
                        text = "${forecastDay[currentPage].day.maxTempC.toInt()}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 80.sp,
                        textAlign = TextAlign.End
                    )
                    Text(
                        modifier = Modifier.padding(top = 10.dp),
                        text = "°",
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                    )
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        text = forecastDay[currentPage].day.condition.text,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        maxLines = 1
                    )

                    Text(
                        text = viewModel.dateConverter(forecastDay[currentPage].date),
                        fontSize = 16.sp
                    )
                }

                Spacer(modifier = Modifier.height(50.dp))

                AnimatedVisibility(
                    visible = showExtended,
                    enter = slideInVertically(
                        tween(300),
                        initialOffsetY = { targetOffset -> targetOffset }
                    ),
                    exit = slideOutVertically(
                        tween(300),
                        targetOffsetY = { targetOffset -> targetOffset }
                    )
                ){
                    ExtendedView(forecastDay[currentPage])
                }
            }
        }
    }
}

@Composable
fun ExtendedView(
    forecastDay: ForecastDay
) {
    Card(
        modifier = Modifier.width(300.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onSurfaceVariant),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ){
            Row(
                modifier = Modifier.padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                DetailedEntries(
                    textTop = "Humidity",
                    icon = ImageVector.vectorResource(id = R.drawable.wi_raindrop),
                    textBottom = "${forecastDay.day.avgHumidity}%"
                )

                DetailedEntries(
                    textTop = "Wind",
                    icon = ImageVector.vectorResource(id = R.drawable.wi_windy),
                    textBottom = "${forecastDay.day.maxWindMph} Mph"
                )

                DetailedEntries(
                    textTop = "Visibility",
                    icon = ImageVector.vectorResource(id = R.drawable.wi_visbility),
                    textBottom = "${forecastDay.day.avgVisMiles} Miles"
                )
            }

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            )

            Row(
                modifier = Modifier.padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                DetailedEntries(
                    textTop = "Max ${forecastDay.day.maxTempC}",
                    icon = ImageVector.vectorResource(id = R.drawable.wi_thermometer),
                    textBottom = "Min ${forecastDay.day.minTempC}"
                )

                DetailedEntries(
                    textTop = "Rain",
                    icon = ImageVector.vectorResource(id = R.drawable.wi_raindrops),
                    textBottom = "${forecastDay.day.dailyChanceOfRain}%"
                )

                DetailedEntries(
                    textTop = "Snow",
                    icon = ImageVector.vectorResource(id = R.drawable.wi_snowflake_cold),
                    textBottom = "${forecastDay.day.dailyChanceOfSnow}%"
                )
            }
        }
    }
}

@Composable
fun DetailedEntries(
    textTop: String,
    icon: ImageVector,
    textBottom: String
){
    Column(
        modifier = Modifier.size(90.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = textBottom,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            maxLines = 1,
            color = MaterialTheme.colorScheme.onSecondary
        )

        Icon(
            modifier = Modifier.size(40.dp),
            imageVector = icon,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onSecondary
        )

        Text(
            text = textTop,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            maxLines = 1,
            color = MaterialTheme.colorScheme.onSecondary
        )
    }
}
