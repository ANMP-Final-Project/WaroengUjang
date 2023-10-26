package my.anmp.waroengujang.view.mainmenu.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import my.anmp.waroengujang.data.model.Menu
import my.anmp.waroengujang.databinding.ItemCartBinding
import my.anmp.waroengujang.util.loadImage

class CartAdapter(private val onItemChanged: (List<Menu>) -> Unit) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    private val dataset = mutableListOf<Menu>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(
            ItemCartBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        return holder.createView(position)
    }

    override fun getItemCount(): Int = dataset.size

    fun changeDataSet(data: List<Menu>) {
        if (data.isNotEmpty()) {
            dataset.clear()
            dataset.addAll(data)
            notifyDataSetChanged()
        }
    }

    inner class CartViewHolder(private val itemBinding: ItemCartBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun createView(position: Int) {
            with(itemBinding) {
                loadImage(this.root.context, dataset[position].thumbnail ?: "", ivThumbnail)
                tvTilte.text = dataset[position].title
                tvPrice.text = "Rp. " + (dataset[position].price!! * dataset[position].quantity).toString()
                tvQuantity.text = dataset[position].quantity.toString()

                btnMinus.setOnClickListener {
                    dataset[position].quantity--
                    if (dataset[position].quantity <= 0) {
                        dataset.removeAt(position)
                    }
                    onItemChanged(dataset)
                    notifyDataSetChanged()
                }
                btnPlus.setOnClickListener {
                    dataset[position].quantity++
                    onItemChanged(dataset)
                    notifyDataSetChanged()
                }
            }
        }
    }
}