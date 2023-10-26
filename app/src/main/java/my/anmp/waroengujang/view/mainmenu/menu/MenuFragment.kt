package my.anmp.waroengujang.view.mainmenu.menu

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import my.anmp.waroengujang.R
import my.anmp.waroengujang.data.ApiFactory
import my.anmp.waroengujang.databinding.FragmentMenuBinding
import my.anmp.waroengujang.view.mainmenu.MainActivity

class MenuFragment : Fragment(R.layout.fragment_menu) {
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { MenuViewModel(ApiFactory.getInstance(requireContext())) }

    private val parentActivity by lazy { requireActivity() as MainActivity }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val menuAdapter = MenuAdapter {
            findNavController().navigate(
                R.id.action_nav_menu_to_detailMenuFragment,
                bundleOf(Pair("data", it))
            )
            Log.d("TAG", "item clicked ${it.title}")
        }
     
        binding.rvMenu.adapter = menuAdapter

        binding.fabTable.setOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.listOfMenu.observe(viewLifecycleOwner) {
            menuAdapter.changeDataSet(it)
        }

        parentActivity.tableService.observe(viewLifecycleOwner) {
            if (it == 0) {
                binding.fabTable.text = "Curently serving table : None"
            } else {
                binding.fabTable.text = "Curently serving table : $it"
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}