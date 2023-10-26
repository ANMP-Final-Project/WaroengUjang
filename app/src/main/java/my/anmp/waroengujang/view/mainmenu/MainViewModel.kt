package my.anmp.waroengujang.view.mainmenu

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val tableService = MutableLiveData<Int>()

    init {
        tableService.value = 0
    }

    fun updateTableService(value:Int){
        tableService.value = value
    }
}