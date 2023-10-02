package my.anmp.waroengujang.data.api

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import my.anmp.waroengujang.data.ApiFactory
import my.anmp.waroengujang.data.InvokeResponse

class GetAllProductRequest {
    val url = "https://dummyjson.com/products"
    fun request(onResult: InvokeResponse): StringRequest {
        return StringRequest(
            Request.Method.GET, url, {
                onResult.onSuccess(it)
            }, {
                onResult.onError(it)
            }
        )
    }
}