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

class HomeFragment : Fragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val parentActivity by lazy { requireActivity() as MainActivity }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
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

        parentActivity.sharedMainViewModel.tableService.observe(viewLifecycleOwner) {
            if (it == 0) {
                binding.tvTableNumber.text = "None"
            } else {
                binding.tvTableNumber.text = it.toString()
            }
        }

        with(binding) {
            btnSubmit.setOnClickListener {
                parentActivity.sharedMainViewModel.updateTableService(
                    binding.etTableNumber.text.toString().toInt()
                )
                shortToast(
                    requireContext(),
                    "Table change to number : ${parentActivity.sharedMainViewModel.tableService.value}"
                )
            }

            tvName.text = userData.name
            loadImage(requireContext(), userData.profilePic ?: "", ivProfilePic)
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}