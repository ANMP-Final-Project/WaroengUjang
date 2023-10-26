package my.anmp.waroengujang.view.mainmenu.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import my.anmp.waroengujang.R
import my.anmp.waroengujang.databinding.FragmentCartBinding
import my.anmp.waroengujang.util.showAlert
import my.anmp.waroengujang.view.mainmenu.MainActivity

class CartFragment : Fragment(R.layout.fragment_cart) {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private val parentActivity by lazy { requireActivity() as MainActivity }

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

        val cartAdapter = CartAdapter(onItemChanged = {
            parentActivity.sharedMainViewModel.changeMenu(it)
        })

        binding.rvListMenuOrder.adapter = cartAdapter
        binding.btnSubmit.setOnClickListener {
            showAlert(requireContext(), msg = "Order successfully process to kitchen")
            parentActivity.sharedMainViewModel.clearMenu()
        }

        parentActivity.sharedMainViewModel.listOfMenu.observe(viewLifecycleOwner) {
            cartAdapter.changeDataSet(it)
            val total = it.sumOf { menu -> (menu.quantity * menu.price!!) }
            binding.tvSubtotal.text = "Rp. ${total}"
            binding.tvTax.text = "Rp. ${(0.1 * total).toInt()}"
            binding.tvTotal.text = "Rp. ${total - (0.1 * total).toInt()}"
        }

        parentActivity.sharedMainViewModel.tableService.observe(viewLifecycleOwner) {
            binding.tvTableNumber.text = it.toString()
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}