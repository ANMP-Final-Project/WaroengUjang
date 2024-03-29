package my.anmp.waroengujang.data.model
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class Menu(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id:Int = 0,
    @SerializedName("desc")
    val desc: String? = "",
    @SerializedName("price")
    val price: Int? = 0,
    @SerializedName("thumbnail")
    val thumbnail: String? = "",
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("quantity")
    var quantity: Int = 0
): Serializable