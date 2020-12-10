package ir.r3za.dinmvrx

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import ir.r3za.dinmvrx.base.BaseFragment
import ir.r3za.dinmvrx.databinding.FragmentListBinding

class ListFragment : BaseFragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by fragmentViewModel()
    private val adapter = FoodsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvFoods.layoutManager = LinearLayoutManager(context)
        binding.rvFoods.adapter = adapter
    }

    override fun invalidate() {
        withState(viewModel) { state ->
            if (state.foodList.complete) {
                adapter.updateItems(state.foodList()!!)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}