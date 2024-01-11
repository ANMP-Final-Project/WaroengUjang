package my.anmp.waroengujang.view.mainmenu.account

import androidx.lifecycle.ViewModel
import my.anmp.waroengujang.data.database.AppDB
import my.anmp.waroengujang.data.model.User

class AccountViewModel(private val roomDB: AppDB):ViewModel() {
    fun deleteUserFromDb(user: User){
        roomDB.userDao().deleteUser(user)
    }
}