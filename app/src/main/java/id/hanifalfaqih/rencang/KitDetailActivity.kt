package id.hanifalfaqih.rencang

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class KitDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kit_detail)

        // Retrieve data from intent
        val kitName = intent.getStringExtra("EXTRA_KIT_NAME")
        val harvestTime = intent.getStringExtra("EXTRA_HARVEST_TIME")
        val imageResource = intent.getIntExtra("EXTRA_IMAGE_RESOURCE", 0) // Default to 0 if not found

        // Find views
        val ivHeaderImage = findViewById<ImageView>(R.id.iv_header_image)
        val tvKitName = findViewById<TextView>(R.id.tv_kit_name)
        val tvHarvestTime = findViewById<TextView>(R.id.tv_harvest_time)
        val backButton: ImageButton = findViewById(R.id.btn_back)
        val startJourneyButton: MaterialButton = findViewById(R.id.btn_start_journey)

        // Set data to views
        tvKitName.text = kitName
        tvHarvestTime.text = harvestTime
        if (imageResource != 0) {
            ivHeaderImage.setImageResource(imageResource)
        } else {
            // Optionally, set a default/placeholder image if no resource ID is passed or it's invalid
            ivHeaderImage.setImageResource(android.R.drawable.ic_menu_gallery) // Example placeholder
        }

        backButton.setOnClickListener {
            finish() // Closes the current activity and returns to the previous one
        }

        startJourneyButton.setOnClickListener {
            Toast.makeText(this, "Memulai perjalanan... Membuka Dashboard.", Toast.LENGTH_SHORT).show()
            // Here you would typically navigate to the DashboardActivity
             val intent = Intent(this, DashboardActivity::class.java)
             startActivity(intent)
        }
    }
}
