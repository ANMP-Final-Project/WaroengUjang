package my.anmp.waroengujang.view.mainmenu.menu

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.VolleyError
import my.anmp.waroengujang.data.ApiFactory
import my.anmp.waroengujang.data.InvokeResponse
import my.anmp.waroengujang.data.api.AuthRequest
import my.anmp.waroengujang.data.api.GetAllMenuRequest
import my.anmp.waroengujang.data.model.Menu
import my.anmp.waroengujang.data.model.User
import my.anmp.waroengujang.util.convertJsonToObject

class MenuViewModel(private val apiFactory: ApiFactory) : ViewModel() {
    private val _listOfMenu = MutableLiveData<List<Menu>>()
    private val _errorMessage = MutableLiveData<List<String>>()
    val listOfMenu: LiveData<List<Menu>> get() = _listOfMenu
    val errorMessage: LiveData<List<String>> get() = _errorMessage

    init {
        fetchListMenu()
    }

    private fun fetchListMenu() {
        _errorMessage.value = emptyList()
        apiFactory.addToRequestQueue(GetAllMenuRequest().request(object : InvokeResponse {
            override fun onSuccess(jsonString: String) {
                Log.d("TAG", "onSuccess:$jsonString")
                val result = convertJsonToObject<Array<Menu>>(jsonString)
                _listOfMenu.postValue(result.toList())
            }

            override fun onError(volleyError: VolleyError) {
                _errorMessage.value =
                    _errorMessage.value.orEmpty() + (volleyError.message ?: "Undefined")
                Log.e("error", _errorMessage.value.toString())
            }
        }))
    }
}