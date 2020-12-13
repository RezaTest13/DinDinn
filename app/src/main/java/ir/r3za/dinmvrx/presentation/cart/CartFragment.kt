package ir.r3za.dinmvrx.presentation.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import ir.r3za.dinmvrx.base.BaseFragment
import ir.r3za.dinmvrx.data.ShoppingCart
import ir.r3za.dinmvrx.databinding.FragmentCartBinding
import ir.r3za.dinmvrx.presentation.formattedPrice

class CartFragment : BaseFragment() {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private val cartAdapter = CartAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvCart.layoutManager = LinearLayoutManager(context)
        binding.rvCart.adapter = cartAdapter
        cartAdapter.updateItems(ShoppingCart.cart)
        binding.tvPrice.text = ShoppingCart.getTotalPrice().formattedPrice(requireContext())
    }

    override fun invalidate() {
    }
}