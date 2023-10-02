package my.anmp.waroengujang.data.model
import com.google.gson.annotations.SerializedName


data class Products(
    @SerializedName("limit")
    val limit: Int? = 0,
    @SerializedName("products")
    val products: List<Product>? = listOf(),
    @SerializedName("skip")
    val skip: Int? = 0,
    @SerializedName("total")
    val total: Int? = 0
)

data class Product(
    @SerializedName("brand")
    val brand: String? = "",
    @SerializedName("category")
    val category: String? = "",
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("discountPercentage")
    val discountPercentage: Double? = 0.0,
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("images")
    val images: List<String?>? = listOf(),
    @SerializedName("price")
    val price: Int? = 0,
    @SerializedName("rating")
    val rating: Double? = 0.0,
    @SerializedName("stock")
    val stock: Int? = 0,
    @SerializedName("thumbnail")
    val thumbnail: String? = "",
    @SerializedName("title")
    val title: String? = ""
)