package my.anmp.waroengujang.view.auth.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.VolleyError
import my.anmp.waroengujang.data.ApiFactory
import my.anmp.waroengujang.data.InvokeResponse
import my.anmp.waroengujang.data.api.AuthRequest
import my.anmp.waroengujang.data.database.AppDB
import my.anmp.waroengujang.data.model.User
import my.anmp.waroengujang.util.convertJsonToObject

class LoginViewModel(private val apiFactory: ApiFactory,private val roomDb: AppDB) : ViewModel() {
    private val _userData = MutableLiveData<User>()
    private val _errorMessage = MutableLiveData<List<String>>()
    val userData: LiveData<User> get() = _userData
    val errorMessage: LiveData<List<String>> get() = _errorMessage

    var username:String = ""
    var password:String = ""

    fun fetchUserData() {
        _errorMessage.value = emptyList()
        apiFactory.addToRequestQueue(AuthRequest().request(object : InvokeResponse {
            override fun onSuccess(jsonString: String) {
                val result = convertJsonToObject<User>(jsonString)
                if (result.name == username && result.password == password) {
                    _userData.postValue(result)
                    return
                }
                _errorMessage.value = _errorMessage.value.orEmpty() + "User not found"
                Log.e("error", _errorMessage.value.toString())
            }

            override fun onError(volleyError: VolleyError) {
                _errorMessage.value = _errorMessage.value.orEmpty() + (volleyError.message ?: "Undefined")
                Log.e("error", _errorMessage.value.toString())
            }
        }))
    }

    fun insertUserToDb(){
        userData.value?.let { roomDb.userDao().insertUser(it) }
    }
}