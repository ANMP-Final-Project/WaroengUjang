package my.anmp.waroengujang.view.mainmenu.menu

import my.anmp.waroengujang.data.model.Menu

interface MenuEventHandler {
    fun onClickServingTable()
    fun onMenuItemClickListener(menu: Menu)

    fun onSearchChanged()
}