package id.hanifalfaqih.rencang

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ChooseKitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_kit)

        val recyclerView: RecyclerView = findViewById(R.id.rv_kit_list)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        val kits = listOf(
            PlantingKit(
                imageResource = android.R.drawable.ic_menu_gallery,
                kitName = "Spicy Chili Kit",
                harvestTime = "Harvest in 28 days"
            ),
            PlantingKit(
                imageResource = android.R.drawable.ic_menu_gallery,
                kitName = "Speedy Kangkung Kit",
                harvestTime = "Harvest in 21 days"
            ),
            PlantingKit(
                imageResource = android.R.drawable.ic_menu_gallery,
                kitName = "Cherry Tomato Kit",
                harvestTime = "Harvest in 35 days"
            ),
            PlantingKit(
                imageResource = android.R.drawable.ic_menu_gallery,
                kitName = "Crispy Lettuce Kit",
                harvestTime = "Harvest in 25 days"
            )
        )

        val adapter = KitAdapter(kits) { selectedKit ->
            // Logic to execute when an item is clicked
            val intent = Intent(this, KitDetailActivity::class.java)

            // Pass data to the detail activity
            intent.putExtra("EXTRA_KIT_NAME", selectedKit.kitName)
            intent.putExtra("EXTRA_HARVEST_TIME", selectedKit.harvestTime)
            intent.putExtra("EXTRA_IMAGE_RESOURCE", selectedKit.imageResource) // Pass image resource ID

            startActivity(intent)
        }
        recyclerView.adapter = adapter
    }
}
