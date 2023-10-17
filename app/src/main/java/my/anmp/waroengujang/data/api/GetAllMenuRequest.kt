package my.anmp.waroengujang.data.api

import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import my.anmp.waroengujang.data.ApiFactory
import my.anmp.waroengujang.data.InvokeResponse

class GetAllMenuRequest {
    private val url = "${ApiFactory.baseUrl}api/makanan.json"
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