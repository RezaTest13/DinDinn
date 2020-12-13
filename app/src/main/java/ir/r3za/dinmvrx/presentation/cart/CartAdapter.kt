package ir.r3za.dinmvrx.presentation.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.r3za.dinmvrx.data.model.CartItem
import ir.r3za.dinmvrx.data.model.FoodItem
import ir.r3za.dinmvrx.databinding.ItemCartBinding
import ir.r3za.dinmvrx.presentation.formattedPrice

class CartAdapter : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private val items: MutableList<CartItem> = mutableListOf()
    var onDeleteClicked: (FoodItem) -> Unit = {}

    fun updateItems(cart: List<CartItem>) {
        val diffCallback = CartDiffCallback(items, cart)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        items.clear()
        items.addAll(cart)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(
            ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onDeleteClicked
        )
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class CartViewHolder(
        private val binding: ItemCartBinding,
        onDeleteClicked: (FoodItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var currentItem: CartItem

        init {
            binding.ivDelete.setOnClickListener {
                onDeleteClicked.invoke(currentItem.foodItem)
            }
        }

        fun bind(item: CartItem) {
            currentItem = item
            Glide.with(itemView).load(item.foodItem.imageUrl).into(binding.ivFood)
            binding.tvTitle.text = item.foodItem.name
            binding.tvPrice.text = item.foodItem.price.multiply(item.count.toBigDecimal())
                .formattedPrice(itemView.context)

        }
    }

    class CartDiffCallback(
        private val oldList: List<CartItem>,
        private val newList: List<CartItem>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].foodItem.id == newList[newItemPosition].foodItem.id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            if (oldList[oldItemPosition].count == newList[newItemPosition].count) {
                return true
            }
            return false
        }

    }
}