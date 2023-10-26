package my.anmp.waroengujang.data.model
import com.google.gson.annotations.SerializedName



data class Order(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("listOfMenu")
    val listOfMenu: MutableList<Menu>? = mutableListOf(),
    @SerializedName("table")
    val table: Int? = 0,
    var timer: String = ""
)