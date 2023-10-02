package my.anmp.waroengujang.view.mainmenu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import my.anmp.waroengujang.data.model.Product
import my.anmp.waroengujang.databinding.LayoutItemMainBinding
import my.anmp.waroengujang.util.loadImage

class MainMenuAdapter : RecyclerView.Adapter<MainMenuAdapter.MainMenuViewHolder>() {
    val dataset = mutableListOf<Product>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainMenuViewHolder {
        return MainMenuViewHolder(
            LayoutItemMainBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MainMenuViewHolder, position: Int) =
        holder.attachView(position)

    override fun getItemCount(): Int = dataset.size

    fun changeDataSet(data: List<Product>) {
        dataset.clear()
        dataset.addAll(data)
        notifyDataSetChanged()
    }

    inner class MainMenuViewHolder(private val itemBinding: LayoutItemMainBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun attachView(position: Int) {
            itemBinding.tvTitle.text = dataset[position].title
            itemBinding.tvBrand.text = dataset[position].brand
            itemBinding.tvPrice.text = "$${dataset[position].price.toString()} usd"
            loadImage(
                itemBinding.root.context,
                dataset[position].thumbnail ?: "",
                itemBinding.ivProduct
            )
            itemBinding.tvRating.rating = dataset[position].rating?.toFloat() ?: 0f
        }
    }
}