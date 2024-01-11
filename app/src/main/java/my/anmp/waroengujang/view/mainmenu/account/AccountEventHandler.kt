package my.anmp.waroengujang.view.mainmenu.account

import my.anmp.waroengujang.data.model.User

interface AccountEventHandler {
    fun onSubmitClick()
    fun onLogoutClick(userData: User)
}