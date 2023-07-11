package app.utility.weatherassist.Data.IconHandler

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import app.utility.weatherassist.R

class WeatherIconDisplay(){

    fun iconDisplay(weatherCode: Int, isDay: Int): Int {

        return if(isDay == 1){
            when (weatherCode) {
                1000 -> R.drawable.day_113
                1003 -> R.drawable.day_116
                in listOf(1006, 1009) -> R.drawable.day_119_122
                1030 -> R.drawable.day_143
                in listOf(1063, 1066, 1180, 1186, 1240) -> R.drawable.day_176_179_293_299_353
                in listOf(1069, 1249) -> R.drawable.day_182_362
                1072 -> R.drawable.day_185
                in listOf(1087, 1273) -> R.drawable.day_200_386
                1114 -> R.drawable.day_227
                1117 -> R.drawable.day_230
                1135 -> R.drawable.day_248
                1147 -> R.drawable.day_260
                in listOf(1150, 1153) -> R.drawable.day_263_266
                1168 -> R.drawable.day_281
                1171 -> R.drawable.day_284
                in listOf(1183, 1189) -> R.drawable.day_296_302
                in listOf(1192, 1243) -> R.drawable.day_305_356
                1195 -> R.drawable.day_308
                in listOf(1198, 1201) -> R.drawable.day_311_314
                in listOf(1204, 1207) -> R.drawable.day_317_320
                in listOf(1210, 1216, 1255) -> R.drawable.day_323_329_368
                in listOf(1213, 1219) -> R.drawable.day_326_332
                in listOf(1222, 1258) -> R.drawable.day_335_371
                1225 -> R.drawable.day_338
                1237 -> R.drawable.day_350
                1246 -> R.drawable.day_359
                1252 -> R.drawable.day_365
                1261 -> R.drawable.day_374
                1264 -> R.drawable.day_377
                1276 -> R.drawable.day_389
                1279 -> R.drawable.day_392
                else -> R.drawable.day_395
            }
        } else {
            when (weatherCode) {
                1000 -> R.drawable.night_113
                1003 -> R.drawable.night_116
                in listOf(1006, 1009) -> R.drawable.night_119_122
                1030 -> R.drawable.night_143
                in listOf(1063, 1066, 1180, 1186, 1240) -> R.drawable.night_176_179_293_299_353
                in listOf(1069, 1249) -> R.drawable.night_182_362
                1072 -> R.drawable.night_185
                in listOf(1087, 1273) -> R.drawable.night_200_386
                1114 -> R.drawable.night_227
                1117 -> R.drawable.night_230
                1135 -> R.drawable.night_248
                1147 -> R.drawable.night_260
                in listOf(1150, 1153) -> R.drawable.night_263_266
                1168 -> R.drawable.night_281
                1171 -> R.drawable.night_284
                in listOf(1183, 1189) -> R.drawable.night_296_302
                in listOf(1192, 1243) -> R.drawable.night_305_356
                1195 -> R.drawable.night_308
                in listOf(1198, 1201) -> R.drawable.night_311_314
                in listOf(1204, 1207) -> R.drawable.night_317_320
                in listOf(1210, 1216, 1255) -> R.drawable.night_323_329_368
                in listOf(1213, 1219) -> R.drawable.night_326_332
                in listOf(1222, 1258) -> R.drawable.night_335_371
                1225 -> R.drawable.night_338
                1237 -> R.drawable.night_350
                1246 -> R.drawable.night_359
                1252 -> R.drawable.night_365
                1261 -> R.drawable.night_374
                1264 -> R.drawable.night_377
                1276 -> R.drawable.night_389
                1279 -> R.drawable.night_392
                else -> R.drawable.night_395
            }
        }
    }
}