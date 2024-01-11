package my.anmp.waroengujang.view.mainmenu

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import my.anmp.waroengujang.data.database.AppDB
import my.anmp.waroengujang.data.model.Menu
import my.anmp.waroengujang.data.model.Order
import my.anmp.waroengujang.data.model.OrderMenu

class MainViewModel(private val roomDb: AppDB) : ViewModel() {
    val tableService = MutableLiveData<Int>()
    val listOfMenu = MutableLiveData<ArrayList<Menu>>()
    var order: Order = Order()
    var tableNumber: String = ""

    init {
        tableService.value = 0
        listOfMenu.value = arrayListOf()
    }

    fun updateTableService(value: Int) {
        tableService.value = value
        order.table = value
    }

    fun addMenu(menu: Menu) {
        if(listOfMenu.value!!.contains(menu)){
            throw IllegalArgumentException("Menu was added before")
        }
        val temp = listOfMenu.value
        temp?.add(menu)
        listOfMenu.value = temp ?: return
    }

    fun changeMenu(menu: List<Menu>) {
        listOfMenu.value?.clear()
        val temp = listOfMenu.value
        temp?.addAll(menu)
        listOfMenu.value = temp ?: return
    }

    fun clearMenu() {
        val temp = listOfMenu.value
        temp?.clear()
        listOfMenu.value = temp ?: return
    }

    fun storeOrderToDb() {
        order.table = tableService.value
        roomDb.orderDao().insertOrder(order)
        order = roomDb.orderDao().getRecentOrder()
    }

    fun storeMenuCartToDb(menu: OrderMenu) {
        roomDb.orderDao().insertOrderMenu(menu)
    }
}