package ir.r3za.dinmvrx.presentation.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.r3za.dinmvrx.databinding.ItemTopPagerBinding

class TopPagerAdapter : RecyclerView.Adapter<TopPagerAdapter.TopPagerViewHolder>() {

    private val imageUrls: MutableList<String> = mutableListOf()

    fun updateItems(newImageUrls: List<String>) {
        imageUrls.clear()
        imageUrls.addAll(newImageUrls)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopPagerViewHolder {
        return TopPagerViewHolder(
            ItemTopPagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = imageUrls.size

    override fun onBindViewHolder(holder: TopPagerViewHolder, position: Int) {
        holder.bind(imageUrls[position])
    }

    class TopPagerViewHolder(val binding: ItemTopPagerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(imageUrl: String) {
            Glide.with(itemView.context).load(imageUrl).into(binding.ivImage)
        }
    }
}