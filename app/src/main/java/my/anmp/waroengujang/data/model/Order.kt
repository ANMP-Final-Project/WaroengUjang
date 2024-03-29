package my.anmp.waroengujang.data.model
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity
data class Order(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("table")
    var table: Int? = 0,
    @SerializedName("timer")
    var timer: String = ""
):Serializable{
    @Ignore
    @SerializedName("listOfMenu")
    val listOfMenu: MutableList<OrderMenu> = mutableListOf()
}