package my.anmp.waroengujang.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable
@Entity
data class User(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("profile_pic")
    val profilePic: String? = "",
    @SerializedName("work_since")
    val workSince: String? = "",
    @SerializedName("password")
    val password: String? = "",
    @SerializedName("token")
    val tokenSession: String? = "ashdkfjhaskdhfaksdhf2341234234"
):Serializable
