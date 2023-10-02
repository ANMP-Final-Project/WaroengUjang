package my.anmp.waroengujang.data.model

import com.google.gson.annotations.SerializedName

data class CarsItem(
    @SerializedName("color")
    val color: String? = "",
    @SerializedName("features")
    val features: List<String?>? = listOf(),
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("make")
    val make: String? = "",
    @SerializedName("model")
    val model: String? = "",
    @SerializedName("price")
    val price: Int? = 0,
    @SerializedName("specs")
    val specs: Specs? = Specs(),
    @SerializedName("year")
    val year: Int? = 0
)

data class Specs(
    @SerializedName("engine")
    val engine: String? = "",
    @SerializedName("fuel_type")
    val fuelType: String? = "",
    @SerializedName("transmission")
    val transmission: String? = ""
)