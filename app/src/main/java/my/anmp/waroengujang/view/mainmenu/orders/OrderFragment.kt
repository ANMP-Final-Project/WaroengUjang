package my.anmp.waroengujang.view.mainmenu.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import my.anmp.waroengujang.R
import my.anmp.waroengujang.data.ApiFactory
import my.anmp.waroengujang.data.model.Order
import my.anmp.waroengujang.databinding.FragmentOrderBinding
import my.anmp.waroengujang.util.showChooseAlert

class OrderFragment : Fragment(R.layout.fragment_order) {
    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { OrderViewModel(ApiFactory(requireContext())) }

    private lateinit var adapter: OrderAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = OrderAdapter {
            showAlert(it)
        }

        binding.rvListOrder.adapter = adapter

        viewModel.listOfOrder.observe(viewLifecycleOwner) {
            adapter.changeDataSet(it)
        }
    }

    private fun showAlert(order: Order) {
        showChooseAlert(
            requireContext(),
            "Table : ${order.table}",
            "Order : ${order.listOfMenu?.map { "${it.title} x ${it.quantity}"}}",
            positiveButton = "Order More",
            negativeButton = "Close Bill",
            onConfirm = {
                findNavController().navigate(R.id.nav_menu)
                it.dismiss()
            },
            onDecline = {
                adapter.deleteDataSet(order)
                it.dismiss()
            }
        )
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}