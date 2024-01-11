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
import my.anmp.waroengujang.data.database.AppDB
import my.anmp.waroengujang.data.model.Menu
import my.anmp.waroengujang.databinding.FragmentMenuBinding
import my.anmp.waroengujang.view.mainmenu.MainActivity

class MenuFragment : Fragment(R.layout.fragment_menu), MenuEventHandler {
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy {
        MenuViewModel(
            AppDB.getInstance(requireActivity().applicationContext)
        )
    }

    private val parentActivity by lazy { requireActivity() as MainActivity }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val menuAdapter = MenuAdapter {
            onMenuItemClickListener(it)
        }
        binding.viewModel = viewModel
        with(binding) {
            adapter = menuAdapter
            eventHandler = this@MenuFragment
        }

        viewModel.listOfMenu.observe(viewLifecycleOwner) {
            menuAdapter.changeDataSet(it)
        }

        parentActivity.sharedMainViewModel.tableService.observe(viewLifecycleOwner) {
            if (it == 0) {
                viewModel.tableServe = "Curently serving table : None"
            } else {
                viewModel.tableServe = "Curently serving table : $it"
            }
        }
    }

    override fun onClickServingTable() {
        findNavController().navigateUp()
    }

    override fun onMenuItemClickListener(menu: Menu) {
        findNavController().navigate(
            R.id.action_nav_menu_to_detailMenuFragment,
            bundleOf(Pair("data", menu))
        )
        Log.d("TAG", "item clicked ${menu.title}")
    }

    override fun onSearchChanged() {

    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}