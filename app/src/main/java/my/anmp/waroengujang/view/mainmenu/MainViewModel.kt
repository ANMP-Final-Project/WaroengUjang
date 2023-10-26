package my.anmp.waroengujang.view.mainmenu

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import my.anmp.waroengujang.data.model.Menu

class MainViewModel : ViewModel() {
    val tableService = MutableLiveData<Int>()
    val listOfMenu = MutableLiveData<ArrayList<Menu>>()

    init {
        tableService.value = 0
        listOfMenu.value = arrayListOf()
    }

    fun updateTableService(value: Int) {
        tableService.value = value
    }

    fun addMenu(menu: Menu) {
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
}