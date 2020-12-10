package ir.r3za.dinmvrx

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.r3za.dinmvrx.data.FoodItem
import ir.r3za.dinmvrx.databinding.ItemFoodBinding
import java.math.BigDecimal

class FoodsAdapter : RecyclerView.Adapter<FoodsAdapter.FoodsViewHolder>() {
    private val items: MutableList<FoodItem> = mutableListOf()

    fun updateItems(newItems: List<FoodItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodsViewHolder {
        return FoodsViewHolder(
            ItemFoodBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: FoodsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class FoodsViewHolder(val binding: ItemFoodBinding) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var foodItem: FoodItem

        init {
            binding.btnAdd.setOnClickListener {

            }
        }

        fun bind(item: FoodItem) {
            foodItem = item
            binding.tvTitle.text = item.name
            binding.tvDescription.text = item.description
            binding.tvIngredients.text = item.specifications
            binding.btnAdd.text = "${item.price.setScale(1, BigDecimal.ROUND_HALF_UP)} USD"
            Glide.with(itemView.context).load(item.imageUrl).into(binding.ivFood)
        }
    }
}