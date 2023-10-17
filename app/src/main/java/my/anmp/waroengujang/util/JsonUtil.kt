package my.anmp.waroengujang.util

import android.content.Context
import com.google.gson.Gson


fun loadJSONFromAsset(context: Context, fileName: String): String {
    return context.assets.open(fileName).bufferedReader().use { it.readText() }
}

inline fun <reified T> convertJsonToObject(stringJson: String): T {
    return Gson().fromJson<T>(stringJson, T::class.java)
}
