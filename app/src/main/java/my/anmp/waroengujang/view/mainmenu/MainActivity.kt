package my.anmp.waroengujang.view.mainmenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import my.anmp.waroengujang.R
import my.anmp.waroengujang.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
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
        binding.tbMain.setupWithNavController(navController,binding.drawerMain)
        binding.mainNavView.setupWithNavController(navController)

    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(binding.drawerMain) || super.onSupportNavigateUp()
    }
}