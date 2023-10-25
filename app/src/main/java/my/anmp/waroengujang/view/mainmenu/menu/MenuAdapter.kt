package my.anmp.waroengujang.view.mainmenu.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import my.anmp.waroengujang.data.model.Menu
import my.anmp.waroengujang.databinding.ItemMenuBinding
import my.anmp.waroengujang.util.loadImage

class MenuAdapter(private val onItemClick: (Menu) -> Unit) :
    RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {
    private val dataset = mutableListOf<Menu>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        return MenuViewHolder(
            ItemMenuBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        return holder.createView(position)
    }

    override fun getItemCount(): Int = dataset.size

    inner class MenuViewHolder(private val itemBinding: ItemMenuBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun createView(position: Int) {
            with(itemBinding) {
                loadImage(this.root.context, dataset[position].thumbnail ?: "", ivMenu)
                tvMenu.text = dataset[position].title
                tvPrice.text = "Rp. ${dataset[position].price.toString()}"
                cvRoot.setOnClickListener {
                    onItemClick(dataset[position])
                }
            }
        }
    }

    fun changeDataSet(listOfMenu: List<Menu>) {
        dataset.clear()
        dataset.addAll(listOfMenu)
        notifyDataSetChanged()
    }
}