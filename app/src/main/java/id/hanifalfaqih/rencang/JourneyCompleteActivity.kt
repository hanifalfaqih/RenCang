package id.hanifalfaqih.rencang

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import nl.dionsegijn.konfetti.xml.KonfettiView
import java.util.concurrent.TimeUnit

class JourneyCompleteActivity : AppCompatActivity() {

    private lateinit var konfettiView: KonfettiView
    private lateinit var btnCreateJournal: Button
    private lateinit var btnNewJourney: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_journey_complete)

        konfettiView = findViewById(R.id.konfettiView)
        btnCreateJournal = findViewById(R.id.btn_create_journal)
        btnNewJourney = findViewById(R.id.btn_new_journey)

        // Start confetti
        val party = Party(
            speed = 0f,
            maxSpeed = 30f,
            damping = 0.9f,
            spread = 360,
            colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def).map { it.toInt() },
            emitter = Emitter(duration = 100, TimeUnit.MILLISECONDS).max(100),
            position = Position.Relative(0.5, 0.3)
        )
        konfettiView.start(party)

        btnCreateJournal.setOnClickListener {
            Toast.makeText(this, "Membuka Jurnal Panen...", Toast.LENGTH_SHORT).show()
            // TODO: Implement navigation to journal creation screen
        }

        btnNewJourney.setOnClickListener {
            // Option 1: Reset progress here
            resetProgressAndReturnToMain()
        }
    }

    private fun resetProgressAndReturnToMain() {
        // Save day 1 to SharedPreferences
        val sharedPref = getSharedPreferences("GardeningAppPrefs", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putInt("currentDay", 1)
            apply()
        }
        Toast.makeText(this, "Memulai perjalanan baru!", Toast.LENGTH_SHORT).show()

        // Navigate back to MainActivity (or another suitable screen)
        val intent = Intent(this, ChooseKitActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish() // Finish this activity
    }
}