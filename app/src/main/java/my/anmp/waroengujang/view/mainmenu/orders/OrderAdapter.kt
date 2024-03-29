package my.anmp.waroengujang.view.mainmenu.orders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import my.anmp.waroengujang.data.model.Order
import my.anmp.waroengujang.databinding.ItemOrderBinding
import timerx.buildStopwatch
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class OrderAdapter(private val onItemClick : (Order,Int) -> Unit) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {
    private val dataSet = mutableListOf<Order>()

    fun changeDataSet(listOfMenu: List<Order>) {
        dataSet.clear()
        dataSet.addAll(listOfMenu)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder(
            ItemOrderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.createView(position)
    }

    fun deleteDataSet(position: Int){
        dataSet.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun getItemCount(): Int = dataSet.size

    inner class OrderViewHolder(private val itemBinding: ItemOrderBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun createView(position: Int) {
            with(itemBinding) {
                var totalMenuPrice = 0
                dataSet[position].listOfMenu.forEach {
                    totalMenuPrice += (it.price?.times(it.quantity) ?: 0)
                }
                data = dataSet[position]
                totalPrice = "Rp. ${totalMenuPrice}"

                buildStopwatch {
                    // Setting the start format of the stopwatch
                    startFormat("MM:SS")
                    // Setting a tick listener that gets notified when time changes
                    onTick { millis: Long, time: CharSequence -> duration = "Duration : $time" }
                    // When the time is equal to one minute, change format to "MM:SS:LL"
                    changeFormatWhen(1, TimeUnit.HOURS, "HH:MM:SS")
                }.start()

                root.setOnClickListener {
                    onItemClick(dataSet[position],position)
                }
            }
        }
    }
}