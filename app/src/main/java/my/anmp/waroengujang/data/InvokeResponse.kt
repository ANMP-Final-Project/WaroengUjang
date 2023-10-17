package my.anmp.waroengujang.data

import com.android.volley.VolleyError

interface InvokeResponse {
    fun onSuccess(jsonString: String)
    fun onError(volleyError: VolleyError)

}