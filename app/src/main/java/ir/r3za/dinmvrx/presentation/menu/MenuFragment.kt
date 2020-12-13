package ir.r3za.dinmvrx.presentation.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import ir.r3za.dinmvrx.R
import ir.r3za.dinmvrx.base.BaseFragment
import ir.r3za.dinmvrx.databinding.FragmentMenuBinding
import ir.r3za.dinmvrx.presentation.cart.CartFragment
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
            AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
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
            navigateToCart()
        }

        binding.tabCategories.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewModel.tabSelected(tab!!.tag as String)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

        viewModel.refreshCart()

        binding.rvFoods.smoothScrollToPosition(0)

        setupObservers()
    }

    private fun navigateToCart() {
        requireActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.fade_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.fade_out
            )
            .add(R.id.fragment_container, CartFragment::class.java, null, null)
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }

    private fun setupObservers() {
        viewModel.selectSubscribe(this, MainState::categories) {
            if (it is Success) {
                it().forEach { foodCategory ->
                    binding.tabCategories.addTab(
                        binding.tabCategories.newTab()
                            .setText(foodCategory.title)
                            .setTag(foodCategory.id)
                    )
                }
            }
        }

        viewModel.selectSubscribe(this, MainState::shoppingCartCount) {
            if (it is Success) {
                binding.viewCart.setCount(it())
            }
        }

        viewModel.selectSubscribe(this, MainState::foodList) {
            if (it is Success) {
                adapter.updateItems(it())
            }
        }

        viewModel.selectSubscribe(this, MainState::topLoading) {
            if (it) {
                binding.pbTop.visibility = View.VISIBLE
            } else {
                binding.pbTop.visibility = View.GONE
            }
        }

        viewModel.selectSubscribe(this, MainState::topPagerEntity) {
            if (it is Success) {
                val topPagerEntity = it()
                pagerAdapter.updateItems(topPagerEntity.imageUrls)
                binding.pagerIndicator.setViewPager(binding.viewPagerTop)
                binding.tvTitle.text = topPagerEntity.title
                binding.tvSubtitle.text = topPagerEntity.subtitle
            }
        }
    }

    override fun invalidate() {
        withState(viewModel) { it ->
            if (it.shoppingCartCount is Success) {
                binding.viewCart.setCount(it.shoppingCartCount() ?: 0)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}