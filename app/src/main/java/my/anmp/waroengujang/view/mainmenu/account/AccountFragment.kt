package my.anmp.waroengujang.view.mainmenu.account

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.dhaval2404.form_validation.rule.NonEmptyRule
import com.github.dhaval2404.form_validation.rule.PasswordRule
import com.github.dhaval2404.form_validation.validation.FormValidator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import my.anmp.waroengujang.R
import my.anmp.waroengujang.data.database.AppDB
import my.anmp.waroengujang.data.model.User
import my.anmp.waroengujang.data.sharedpref.SharedPrefHelper
import my.anmp.waroengujang.databinding.FragmentAccountBinding
import my.anmp.waroengujang.util.showAlert
import my.anmp.waroengujang.view.auth.AuthActivity

class AccountFragment : Fragment(R.layout.fragment_account), AccountEventHandler {
    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { AccountViewModel(AppDB.getInstance(requireContext())) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
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
            binding.data = userData
            binding.eventHandler = this@AccountFragment
        }
    }

    private fun isFormValidated(): Boolean {
        return FormValidator.getInstance()
            .addField(binding.etOldPassword, NonEmptyRule("Must be filled"))
            .addField(
                binding.etNewPassword,
                NonEmptyRule("Must be filled"),
                PasswordRule("Please provide combination of char,number & special char"),
            )
            .addField(
                binding.etReNewPassword,
                NonEmptyRule("Must be filled"),
                PasswordRule("Please provide combination of char,number & special char"),
            ).validate()
    }

    override fun onSubmitClick() {
        if (isFormValidated()) {
            showAlert(requireContext(), "Success", "Password successfully changed")
        }
    }

    override fun onLogoutClick(userData: User) {
        SharedPrefHelper().deleteUser(
            requireContext().getSharedPreferences(
                SharedPrefHelper.authPrefKey,
                Context.MODE_PRIVATE
            )
        )
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.deleteUserFromDb(userData)
        }
        startActivity(Intent(requireContext(), AuthActivity::class.java))
        requireActivity().finish()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}