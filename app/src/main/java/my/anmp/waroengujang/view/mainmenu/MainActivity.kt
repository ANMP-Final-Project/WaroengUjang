package my.anmp.waroengujang.view.mainmenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import my.anmp.waroengujang.R
import my.anmp.waroengujang.data.ApiFactory
import my.anmp.waroengujang.data.model.Product
import my.anmp.waroengujang.data.model.Products
import my.anmp.waroengujang.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnRefreshListener {
    lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainMenuViewModel
    private lateinit var rvAdapter: MainMenuAdapter
    private val apiFactory by lazy { ApiFactory(this.applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = MainMenuViewModel(apiFactory)
        rvAdapter = MainMenuAdapter()
        binding.srMain.setOnRefreshListener(this)
        binding.rvMain.adapter = rvAdapter
        viewModel.productsLiveData.observe(this, Observer {
            rvAdapter.changeDataSet(it)
        })

        viewModel.fetchData()
    }

    override fun onRefresh() {
        viewModel.fetchData()
    }
}
