package id.hanifalfaqih.rencang

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class KitAdapter(
    private val kits: List<PlantingKit>,
    private val onItemClick: (PlantingKit) -> Unit
) : RecyclerView.Adapter<KitAdapter.KitViewHolder>() {

    class KitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val kitImage: ImageView = itemView.findViewById(R.id.iv_kit_image)
        val kitName: TextView = itemView.findViewById(R.id.tv_kit_name)
        val harvestTime: TextView = itemView.findViewById(R.id.tv_harvest_time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KitViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_kit_card, parent, false)
        return KitViewHolder(view)
    }

    override fun onBindViewHolder(holder: KitViewHolder, position: Int) {
        val kit = kits[position]
        holder.kitImage.setImageResource(kit.imageResource)
        holder.kitName.text = kit.kitName
        holder.harvestTime.text = kit.harvestTime
        holder.itemView.setOnClickListener {
            onItemClick(kit)
        }
    }

    override fun getItemCount() = kits.size
}
