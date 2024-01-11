package my.anmp.waroengujang.view.mainmenu.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import my.anmp.waroengujang.R
import my.anmp.waroengujang.data.sharedpref.SharedPrefHelper
import my.anmp.waroengujang.databinding.FragmentHomeBinding
import my.anmp.waroengujang.util.loadImage
import my.anmp.waroengujang.util.shortToast
import my.anmp.waroengujang.view.mainmenu.MainActivity

class HomeFragment : Fragment(R.layout.fragment_home), HomeEventHandler {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val parentActivity by lazy { requireActivity() as MainActivity }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userData = SharedPrefHelper().getUser(
            requireContext().getSharedPreferences(
                SharedPrefHelper.authPrefKey,
                Context.MODE_PRIVATE
            )
        )
        with(binding) {
            sharedViewModel = parentActivity.sharedMainViewModel
            userName = userData.name
            eventHandler = this@HomeFragment
            loadImage(requireContext(), userData.profilePic ?: "", ivProfilePic)
        }

        parentActivity.sharedMainViewModel.tableService.observe(viewLifecycleOwner) {
            if (it == 0) {
                parentActivity.sharedMainViewModel.tableNumber = "0"
            } else {
                parentActivity.sharedMainViewModel.tableNumber = it.toString()
            }
        }
    }

    override fun onBtnSubmit() {
        if (parentActivity.sharedMainViewModel.toString().isEmpty()) {
            shortToast(
                requireContext(),
                "You must fill the table number"
            )
            return
        }
        parentActivity.sharedMainViewModel.updateTableService(
            parentActivity.sharedMainViewModel.tableNumber.toInt()
        )
        shortToast(
            requireContext(),
            "Table change to number : ${parentActivity.sharedMainViewModel.tableService.value}"
        )
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}