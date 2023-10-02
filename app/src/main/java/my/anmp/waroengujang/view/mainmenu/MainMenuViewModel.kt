package my.anmp.waroengujang.view.mainmenu

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.VolleyError
import my.anmp.waroengujang.data.ApiFactory
import my.anmp.waroengujang.data.InvokeResponse
import my.anmp.waroengujang.data.api.GetAllProductRequest
import my.anmp.waroengujang.data.model.Product
import my.anmp.waroengujang.data.model.Products
import my.anmp.waroengujang.util.convertJsonToObject

class MainMenuViewModel(private val apiFactory: ApiFactory) : InvokeResponse, ViewModel() {
    private val getAllProductRequest = GetAllProductRequest()
    val productsLiveData = MutableLiveData<MutableList<Product>>()
    val studentLoadErrorLiveData = MutableLiveData<String>()

    fun fetchData() {
        apiFactory.addToRequestQueue(getAllProductRequest.request(this))
    }

    override fun onSuccess(jsonString: String) {
        val data = convertJsonToObject<Products>(jsonString)
        productsLiveData.value = (data.products as MutableList<Product>?)!!
        Log.d("API RESPONSE", data.toString())
    }

    override fun onError(volleyError: VolleyError) {
        studentLoadErrorLiveData.value = volleyError.localizedMessage
    }
}