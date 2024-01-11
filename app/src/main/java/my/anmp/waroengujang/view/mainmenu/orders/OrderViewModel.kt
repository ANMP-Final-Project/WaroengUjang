package my.anmp.waroengujang.view.mainmenu.orders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import my.anmp.waroengujang.data.database.AppDB
import my.anmp.waroengujang.data.model.Order

class OrderViewModel(private val roomDb: AppDB) : ViewModel() {
    private val _listOfOrder = MutableLiveData<List<Order>>()
    val listOfOrder: LiveData<List<Order>> get() = _listOfOrder

    init {
        viewModelScope.launch { fetchOrderFromPipe() }
    }

    private suspend fun fetchOrderFromPipe() {
        roomDb.orderDao().getAllOrder().collect {
            CoroutineScope(Dispatchers.IO).launch {
                it.map { it.listOfMenu.addAll(roomDb.orderDao().getAllOrderMenu(it.id!!)) }
                _listOfOrder.postValue(it)
            }
        }
    }

    fun deleteOrderFromDb(order: Order) {
        roomDb.orderDao().deleteOrder(order)
    }

    fun deleteOrderMenuFromDb(orderId:Int){
        roomDb.orderDao().deleteAllOrderMenu(orderId)
    }
}