package app.utility.weatherassist.Data.Utilities

import android.os.Build
import androidx.annotation.RequiresApi
import app.utility.weatherassist.Data.RequestModel.Hour
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
class DateConverter {

    fun convertDateToDayOfWeek(dateString: String): String {
        val currentDate = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault())
        val date = LocalDate.parse(dateString, formatter)

        val dayOfWeek = date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
        val dayOfMonth = date.dayOfMonth
        val month = date.month.getDisplayName(TextStyle.SHORT, Locale.getDefault())

        return when (date) {
            currentDate -> {
                "Today, $dayOfMonth $month"
            }
            else -> {
                "$dayOfWeek, $dayOfMonth $month"
            }
        }
    }


    fun convertStringToTime(timeString: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.getDefault())
        val localDateTime = LocalDateTime.parse(timeString, formatter)
        val currentTime = LocalTime.now()
        val currentDate = LocalDateTime.now()

        if (localDateTime.toLocalDate() != currentDate.toLocalDate()) {
            return localDateTime.format(DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault()))
        }

        if (localDateTime.hour == currentTime.hour) {
            return "Now"
        }

        return localDateTime.format(DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault()))
    }

}