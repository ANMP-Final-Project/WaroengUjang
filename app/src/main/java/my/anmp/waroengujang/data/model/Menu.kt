package my.anmp.waroengujang.data.model
import com.google.gson.annotations.SerializedName


data class Menu(
    @SerializedName("desc")
    val desc: String? = "",
    @SerializedName("price")
    val price: Int? = 0,
    @SerializedName("thumbnail")
    val thumbnail: String? = "",
    @SerializedName("title")
    val title: String? = ""
)