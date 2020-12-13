package ir.r3za.dinmvrx.presentation.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.google.android.material.appbar.AppBarLayout
import ir.r3za.dinmvrx.MainViewModel
import ir.r3za.dinmvrx.R
import ir.r3za.dinmvrx.base.BaseFragment
import ir.r3za.dinmvrx.databinding.FragmentMenuBinding
import kotlin.math.abs

class MenuFragment : BaseFragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by fragmentViewModel()
    private val adapter = FoodsAdapter()
    private val pagerAdapter = TopPagerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.appBarLayout.addOnOffsetChangedListener(
            AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
                if (abs(verticalOffset) > 20) {
                    binding.viewCart.show()
                } else {
                    binding.viewCart.hide()
                }
            })
        adapter.onAddClicked = {
            viewModel.addToCart(it)
        }
        binding.rvFoods.layoutManager = LinearLayoutManager(context)
        binding.rvFoods.adapter = adapter
        (binding.rvFoods.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        binding.viewPagerTop.adapter = pagerAdapter
        binding.viewCart.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_cartFragment)
        }
    }

    override fun invalidate() {
        withState(viewModel) { state ->
            if (state.foodList.complete) {
                adapter.updateItems(state.foodList()!!)
            }
            if (state.topLoading) {
                binding.pbTop.visibility = View.VISIBLE
            } else {
                binding.pbTop.visibility = View.GONE
            }
            if (state.topPagerEntity.complete) {
                val topPagerEntity = state.topPagerEntity()!!
                pagerAdapter.updateItems(topPagerEntity.imageUrls)
                binding.pagerIndicator.setViewPager(binding.viewPagerTop)
                binding.tvTitle.text = topPagerEntity.title
                binding.tvSubtitle.text = topPagerEntity.subtitle
            }
            binding.viewCart.setCount(state.shoppingCartCount() ?: 0)
            if (state.categories.complete) {
                if (binding.tabCategories.tabCount >= 1) {
                    return@withState
                }
                state.categories()!!.forEach { foodCategory ->
                    binding.tabCategories.addTab(
                        binding.tabCategories.newTab()
                            .setText(foodCategory.title)
                            .setTag(foodCategory.id)
                    )
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}