package id.hanifalfaqih.rencang

import androidx.annotation.DrawableRes

data class DailyTask(
    val day: Int,
    val taskTitle: String,
    val taskDescription: String,
    @DrawableRes val iconResourceId: Int // e.g., R.drawable.ic_watering_can
)
