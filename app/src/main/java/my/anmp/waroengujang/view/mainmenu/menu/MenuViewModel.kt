package my.anmp.waroengujang.view.mainmenu.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import my.anmp.waroengujang.data.database.AppDB
import my.anmp.waroengujang.data.model.Menu

class MenuViewModel(private val roomDb: AppDB) : ViewModel() {
    private val _listOfMenu = MutableLiveData<List<Menu>>()
    val listOfMenu: LiveData<List<Menu>> get() = _listOfMenu

    var tableServe:String = "0"

    init {
        viewModelScope.launch { getListFromPipe() }
    }

    private suspend fun getListFromPipe() {
        roomDb.menuDao().getAllMenu().collect {
            _listOfMenu.postValue(it)
        }
    }

}