package my.anmp.waroengujang.view.mainmenu.orders

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.VolleyError
import my.anmp.waroengujang.data.ApiFactory
import my.anmp.waroengujang.data.InvokeResponse
import my.anmp.waroengujang.data.api.GetAllOrderRequest
import my.anmp.waroengujang.data.model.Order
import my.anmp.waroengujang.util.convertJsonToObject

class OrderViewModel(private val apiFactory: ApiFactory) : ViewModel() {
    private val _listOfOrder = MutableLiveData<List<Order>>()
    private val _errorMessage = MutableLiveData<List<String>>()
    val listOfOrder: LiveData<List<Order>> get() = _listOfOrder
    val errorMessage: LiveData<List<String>> get() = _errorMessage

    init {
        fetchData()
    }

    private fun fetchData() {
        _errorMessage.value = emptyList()
        apiFactory.addToRequestQueue(GetAllOrderRequest().request(object : InvokeResponse {
            override fun onSuccess(jsonString: String) {
                Log.d("API_REQUEST", "onSuccess:$jsonString")
                val result = convertJsonToObject<Array<Order>>(jsonString)
                _listOfOrder.postValue(result.toList())
            }

            override fun onError(volleyError: VolleyError) {
                _errorMessage.value =
                    _errorMessage.value.orEmpty() + (volleyError.message ?: "Undefined")
                Log.e("error", _errorMessage.value.toString())
            }
        }))
    }
}