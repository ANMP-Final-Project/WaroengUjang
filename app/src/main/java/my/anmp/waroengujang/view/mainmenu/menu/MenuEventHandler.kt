package my.anmp.waroengujang.view.mainmenu.menu

import my.anmp.waroengujang.data.model.Menu

interface MenuEventHandler {
    fun OnClickServingTable()
    fun OnMenuItemClickListener(menu: Menu)

    fun OnSearchChanged()
}