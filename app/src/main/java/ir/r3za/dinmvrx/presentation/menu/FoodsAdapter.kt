package ir.r3za.dinmvrx.presentation.menu

import android.content.res.ColorStateList
import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.r3za.dinmvrx.R
import ir.r3za.dinmvrx.data.model.FoodItem
import ir.r3za.dinmvrx.databinding.ItemFoodBinding
import ir.r3za.dinmvrx.presentation.formattedPrice

class FoodsAdapter : RecyclerView.Adapter<FoodsAdapter.FoodsViewHolder>() {
    private val items: MutableList<FoodItem> = mutableListOf()

    fun updateItems(newItems: List<FoodItem>) {
        val diffCallback = FoodDiffCallback(items, newItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

    var onAddClicked: (FoodItem) -> Unit = {}

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

    inner class FoodsViewHolder(private val binding: ItemFoodBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnAdd.setOnClickListener {
                val clickedPosition = adapterPosition
                onAddClicked(items[clickedPosition])
                items[clickedPosition].adding = true
                notifyItemChanged(clickedPosition)
                Handler().postDelayed({
                    items[clickedPosition].adding = false
                    notifyItemChanged(clickedPosition)
                }, 300)
            }
        }

        fun bind(item: FoodItem) {
            binding.tvTitle.text = item.name
            binding.tvDescription.text = item.description
            binding.tvIngredients.text = item.specifications
            if (item.adding) {
                binding.btnAdd.text = itemView.context.getString(R.string.added_plus_one)
                binding.btnAdd.backgroundTintList = ColorStateList.valueOf(
                    ResourcesCompat.getColor(
                        itemView.context.resources,
                        R.color.colorGreen,
                        itemView.context.theme
                    )
                )
            } else {
                binding.btnAdd.backgroundTintList = ColorStateList.valueOf(
                    ResourcesCompat.getColor(
                        itemView.context.resources,
                        android.R.color.black,
                        itemView.context.theme
                    )
                )
                binding.btnAdd.text = item.price.formattedPrice(itemView.context)
            }
            Glide.with(itemView.context).load(item.imageUrl).into(binding.ivFood)
        }
    }

    class FoodDiffCallback(
        private val oldList: List<FoodItem>,
        private val newList: List<FoodItem>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

    }
}