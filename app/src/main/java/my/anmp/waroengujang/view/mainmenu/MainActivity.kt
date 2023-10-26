package my.anmp.waroengujang.view.mainmenu

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import my.anmp.waroengujang.R
import my.anmp.waroengujang.data.sharedpref.SharedPrefHelper
import my.anmp.waroengujang.databinding.ActivityMainBinding
import my.anmp.waroengujang.view.auth.AuthActivity

class MainActivity : AppCompatActivity() {
    val sharedMainViewModel = MainViewModel()
    lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController
    private val preference by lazy {
        applicationContext.getSharedPreferences(
            SharedPrefHelper.authPrefKey,
            Context.MODE_PRIVATE
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //nav config
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fm_main) as NavHostFragment
        navController = navHostFragment.navController

        //nav attach
        binding.bnvMain.setupWithNavController(navController)
        binding.tbMain.setupWithNavController(navController, binding.drawerMain)
        binding.mainNavView.setupWithNavController(navController)

        if (SharedPrefHelper().getUser(preference).id == 0) {
            startActivity(Intent(this, AuthActivity::class.java))
            this.finish()
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(binding.drawerMain) || super.onSupportNavigateUp()
    }
}