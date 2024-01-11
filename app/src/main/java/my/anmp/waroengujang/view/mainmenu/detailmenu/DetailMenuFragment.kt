package my.anmp.waroengujang.view.mainmenu.detailmenu

import android.os.Bundle
import android.util.Log
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

class DetailMenuFragment : Fragment(R.layout.fragment_detail_menu),DetailMenuEventHandler {
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
            menuData = data
            eventHandler = this@DetailMenuFragment
            loadImage(requireContext(), data.thumbnail ?: "", ivThumbnail)
        }
    }

    override fun onSubmitButtonClick(data:Menu) {
        try {
            parentActivity.sharedMainViewModel.addMenu(data)
            showAlert(requireContext(), msg = "Menu added to cart", title = "Success")
        }catch (e:Exception){
            showAlert(requireContext(), msg = "Menu was added before", title = "Success")
        }
    }

    override fun onAddButtonClick() {
        binding.menuData!!.quantity++
        binding.tvQuantity.text = binding.menuData!!.quantity.toString()
    }

    override fun onMinusButtonClick() {
        if (binding.menuData!!.quantity == 0) {
            return
        }
        binding.menuData!!.quantity--
        binding.tvQuantity.text = binding.menuData!!.quantity.toString()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}