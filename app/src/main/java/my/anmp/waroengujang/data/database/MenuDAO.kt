package my.anmp.waroengujang.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import my.anmp.waroengujang.data.model.Menu

@Dao
interface MenuDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMenu(menu: Menu)

    @Delete()
    fun deleteMenu(menu: Menu)

    @Query("SELECT * FROM MENU")
    fun getAllMenu(): Flow<List<Menu>>

}