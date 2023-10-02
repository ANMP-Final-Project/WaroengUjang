package my.anmp.waroengujang.data.api

import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson
import my.anmp.waroengujang.data.InvokeResponse

class GetAllCarRequest {
    val url = "localhost:8080/cars.json"
    fun request(onResult:InvokeResponse){
        StringRequest(
            Request.Method.GET, url, {
                onResult.onSuccess(it)
            }, {
                onResult.onError(it)
            }
        )
    }
}