package my.anmp.waroengujang.view.mainmenu.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import my.anmp.waroengujang.R
import my.anmp.waroengujang.data.model.Order
import my.anmp.waroengujang.data.model.OrderMenu
import my.anmp.waroengujang.databinding.FragmentCartBinding
import my.anmp.waroengujang.util.shortToast
import my.anmp.waroengujang.view.mainmenu.MainActivity

class CartFragment : Fragment(R.layout.fragment_cart), CartEventHandler {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private val parentActivity by lazy { requireActivity() as MainActivity }

    private lateinit var cartAdapter: CartAdapter

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
        cartAdapter = CartAdapter(onItemChanged = {
            parentActivity.sharedMainViewModel.changeMenu(it)
        })

        with(binding) {
            eventHandler = this@CartFragment
            adapter = cartAdapter
            subTotal = "Rp. 0"
            tax = "Rp. 0"
            total = "Rp. 0"

        }

        parentActivity.sharedMainViewModel.listOfMenu.observe(viewLifecycleOwner) {
            cartAdapter.changeDataSet(it)
            val total = it.sumOf { menu -> (menu.quantity * menu.price!!) }
            binding.subTotal = "Rp. ${total}"
            binding.tax = "Rp. ${(0.1 * total).toInt()}"
            binding.total = "Rp. ${total + (0.1 * total).toInt()}"
        }

        parentActivity.sharedMainViewModel.tableService.observe(viewLifecycleOwner) {
            binding.tableNumber = it.toString()
        }
    }

    override fun onButtonSubmitClick() {
        CoroutineScope(Dispatchers.IO).launch {
            CoroutineScope(Dispatchers.IO).launch {
                parentActivity.sharedMainViewModel.storeOrderToDb()
            }.join()

            CoroutineScope(Dispatchers.IO).launch {
                for (value in cartAdapter.getDataSet()) {
                    parentActivity.sharedMainViewModel.storeMenuCartToDb(
                        OrderMenu(
                            null,
                            parentActivity.sharedMainViewModel.order.id!!,
                            value.title,
                            value.price,
                            value.quantity
                        )
                    )
                }
            }.join()

            CoroutineScope(Dispatchers.Main).launch {
                parentActivity.sharedMainViewModel.clearMenu()
            }.join()

            CoroutineScope(Dispatchers.Main).launch {
                parentActivity.sharedMainViewModel.order = Order()
            }
        }
        shortToast(requireContext(),"Sukses diteruskan ke dapur")
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}