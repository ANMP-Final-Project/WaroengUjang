package my.anmp.waroengujang.view.mainmenu.detailmenu

import my.anmp.waroengujang.data.model.Menu

interface DetailMenuEventHandler {
    fun onAddButtonClick()
    fun onMinusButtonClick()

    fun onSubmitButtonClick(data: Menu)
}