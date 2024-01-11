package my.anmp.waroengujang.view.auth.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import my.anmp.waroengujang.R
import my.anmp.waroengujang.data.ApiFactory
import my.anmp.waroengujang.data.database.AppDB
import my.anmp.waroengujang.data.sharedpref.SharedPrefHelper
import my.anmp.waroengujang.databinding.FragmentLoginBinding
import my.anmp.waroengujang.util.shortToast
import my.anmp.waroengujang.util.showAlert
import my.anmp.waroengujang.view.mainmenu.MainActivity

class LoginFragment : Fragment(R.layout.fragment_login),LoginEventHandler {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy {
        LoginViewModel(
            ApiFactory.getInstance(requireContext()),
            AppDB.getInstance(requireContext())
        )
    }
    private val preference by lazy {
        requireContext().getSharedPreferences(
            SharedPrefHelper.authPrefKey,
            Context.MODE_PRIVATE
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.eventHandler = this

        viewModel.userData.observe(viewLifecycleOwner) {
            if (it.id != 0) {
                SharedPrefHelper().storeUser(preference, it)
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.insertUserToDb()
                }
                shortToast(requireContext(), "Welcome ${it.name}")
                startActivity(Intent(requireContext(), MainActivity::class.java))
                requireActivity().finish()
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()){
                showAlert(requireContext(), msg = it[0])
            }
        }
    }

    override fun onLoginClick() {
        viewModel.fetchUserData()
    }

    override fun onSignUpClick() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}