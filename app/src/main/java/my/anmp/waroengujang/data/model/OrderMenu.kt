package my.anmp.waroengujang.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity
data class OrderMenu(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int?,
    @SerializedName("idOrder")
    val idOrder: Int,
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("price")
    val price: Int? = 0,
    @SerializedName("quantity")
    var quantity: Int = 0
) : Serializable