package my.anmp.waroengujang.data.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("profile_pic")
    val profilePic: String? = "",
    @SerializedName("work_since")
    val workSince: String? = "",
    @SerializedName("password")
    val password: String? = ""
):Serializable
