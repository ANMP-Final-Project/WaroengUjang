package my.anmp.waroengujang.view.auth

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import my.anmp.waroengujang.R
import my.anmp.waroengujang.data.sharedpref.SharedPrefHelper
import my.anmp.waroengujang.databinding.ActivityAuthBinding
import my.anmp.waroengujang.view.mainmenu.MainActivity

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding
    private lateinit var navController: NavController
    private val preference by lazy {
        applicationContext.getSharedPreferences(SharedPrefHelper.authPrefKey, Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //nav config
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fm_auth) as NavHostFragment
        navController = navHostFragment.navController

        if (SharedPrefHelper().getUser(preference).id != 0) {
            startActivity(Intent(this, MainActivity::class.java))
            this.finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }
}