package id.hanifalfaqih.rencang

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
// import com.google.android.material.progressindicator.LinearProgressIndicator // Not used in the new layout

class DashboardActivity : AppCompatActivity() {

    private var currentDay: Int = 1
    private val totalDays by lazy { ChiliPlantRepository.getTotalDays() } // Assuming this repository exists

    // Header
    private lateinit var tvGreeting: TextView
    private lateinit var tvDayProgress: TextView

    // Main Task Card
    private lateinit var ivTaskIcon: ImageView
    private lateinit var tvTaskDescription: TextView
    private lateinit var btnCompleteTask: MaterialButton

    // Status Card
    private lateinit var tvGrowthValue: TextView
    private lateinit var tvHealthValue: TextView
    private lateinit var tvLastWateredValue: TextView
    private lateinit var tvNextFeedValue: TextView

    private val prefsName = "GardeningAppPrefs"
    private val keyCurrentDay = "currentDay"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Initialize Header Views
        tvGreeting = findViewById(R.id.tv_greeting)
        tvDayProgress = findViewById(R.id.tv_day_progress)

        // Initialize Main Task Card Views
        ivTaskIcon = findViewById(R.id.iv_task_icon)
        tvTaskDescription = findViewById(R.id.tv_task_description)
        btnCompleteTask = findViewById(R.id.btn_complete_task)

        // Initialize Status Card Views
        tvGrowthValue = findViewById(R.id.tv_growth_value)
        tvHealthValue = findViewById(R.id.tv_health_value)
        tvLastWateredValue = findViewById(R.id.tv_last_watered_value)
        tvNextFeedValue = findViewById(R.id.tv_next_feed_value)

        currentDay = loadCurrentDay()

        btnCompleteTask.setOnClickListener {
            if (currentDay < totalDays) {
                currentDay++
                saveCurrentDay(currentDay)
                updateUiForCurrentDay()
                Toast.makeText(this, "Lanjut ke hari berikutnya!", Toast.LENGTH_SHORT).show()
            } else if (currentDay == totalDays) {
                saveCurrentDay(currentDay + 1)
                val intent = Intent(this, JourneyCompleteActivity::class.java)
                startActivity(intent)
                // finish() // Optional
            } else {
                // Journey already completed, button might lead to new journey or be disabled
                Toast.makeText(this, "Perjalanan sudah selesai!", Toast.LENGTH_SHORT).show()
                // Example: Redirect to choose a new kit
                // val intent = Intent(this, ChooseKitActivity::class.java)
                // startActivity(intent)
                // finish()
            }
        }
        updateUiForCurrentDay() // Initial UI setup
    }

    private fun saveCurrentDay(day: Int) {
        val sharedPref = getSharedPreferences(prefsName, Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putInt(keyCurrentDay, day)
            apply()
        }
    }

    private fun loadCurrentDay(): Int {
        val sharedPref = getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        return sharedPref.getInt(keyCurrentDay, 1)
    }

    private fun updateUiForCurrentDay() {
        // Update Header
        // You might want to get the user's name from somewhere (e.g., SharedPreferences, ViewModel)
        tvGreeting.text = "Good Morning, Rian!" // Placeholder name
        tvDayProgress.text = "Day $currentDay of $totalDays"

        if (currentDay > totalDays) {
            // Journey Completed State
            tvGreeting.text = "Perjalanan telah selesai!"
            tvDayProgress.text = "Luar Biasa!" // Or hide tvDayProgress

            ivTaskIcon.setImageResource(R.drawable.ic_leaf) // Example: celebration icon
            tvTaskDescription.text = "Anda telah menyelesaikan semua tugas."
            btnCompleteTask.text = "Mulai Perjalanan Baru?"
            btnCompleteTask.setOnClickListener {
                val intent = Intent(this, ChooseKitActivity::class.java) // Or JourneyCompleteActivity
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            }

            // Update Status Card for completed journey (optional)
            tvGrowthValue.text = "Optimal" // Example
            tvHealthValue.text = "Sangat Baik" // Example
            tvLastWateredValue.text = "Sesuai Jadwal" // Example
            tvNextFeedValue.text = "Selesai" // Example
            return
        }

        // In-Progress Journey State
        val currentTask = ChiliPlantRepository.getTasks().find { it.day == currentDay }

        if (currentTask != null) {
            ivTaskIcon.setImageResource(currentTask.iconResourceId) // Make sure this icon exists
            tvTaskDescription.text = currentTask.taskDescription
            btnCompleteTask.text = "Mark as Complete" // Or "Tugas Selesai"
            btnCompleteTask.isEnabled = true
        } else {
            ivTaskIcon.setImageResource(R.drawable.ic_leaf) // Default/Rest icon
            tvTaskDescription.text = "Tidak ada tugas spesifik untuk hari ini. Waktunya observasi!"
            btnCompleteTask.text = "Lanjut ke Hari Berikutnya"
            btnCompleteTask.isEnabled = true
            if (currentDay >= totalDays) {
                btnCompleteTask.text = "Selesaikan Perjalanan"
            }
        }

        // Update Status Card with dynamic data (replace with your actual logic)
        // These are placeholders - you need to fetch this data for the currentDay
        tvGrowthValue.text = "${10 + currentDay}cm tall" // Example dynamic value
        tvHealthValue.text = if (currentDay % 3 == 0) "Needs Check" else "Excellent" // Example
        tvLastWateredValue.text = if (currentDay > 1) "${currentDay -1 }h ago" else "Just Started" // Example
        tvNextFeedValue.text = "In ${2 - (currentDay % 2)} days" // Example
    }
}
