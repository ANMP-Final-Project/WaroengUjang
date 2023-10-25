package my.anmp.waroengujang.view.mainmenu.detailmenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import my.anmp.waroengujang.R
import my.anmp.waroengujang.data.model.Menu
import my.anmp.waroengujang.databinding.FragmentDetailMenuBinding
import my.anmp.waroengujang.util.loadImage
import my.anmp.waroengujang.util.shortToast
import my.anmp.waroengujang.util.showAlert
import my.anmp.waroengujang.view.mainmenu.MainActivity

class DetailMenuFragment : Fragment(R.layout.fragment_detail_menu) {
    private var _binding: FragmentDetailMenuBinding? = null
    private val binding get() = _binding!!

    private val parentActivity by lazy { requireActivity() as MainActivity }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data: Menu? = arguments?.getSerializable("data") as Menu?
        var quantityOrder = 0

        if (data == null) {
            shortToast(requireContext(), "Undefined data")
            findNavController().navigateUp()
            return
        }

        try {
            parentActivity.binding.tbMain.title = "${data.title}"
        } catch (e: Exception) {
            e.printStackTrace()
        }

        with(binding) {
            loadImage(requireContext(), data.thumbnail ?: "", ivThumbnail)
            tvTitle.text = data.title
            tvDesc.text = data.desc
            tvPrice.text = "IDR ${data.price.toString()}"

            btnPlus.setOnClickListener {
                quantityOrder++
                tvQuantity.text = quantityOrder.toString()
            }
            btnMinus.setOnClickListener {
                if (quantityOrder == 0) {
                    return@setOnClickListener
                }
                quantityOrder--
                tvQuantity.text = quantityOrder.toString()
            }

            btnSubmit.setOnClickListener {
                data.quantity = tvQuantity.text.toString().toInt()
                parentActivity.listOfItem.add(data)
                showAlert(requireContext(), msg = "Menu added to cart", title = "Success")
                quantityOrder = 0
                tvQuantity.text = "0"
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}