package my.anmp.waroengujang.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import my.anmp.waroengujang.data.model.Menu
import my.anmp.waroengujang.data.model.User
@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("SELECT * FROM user WHERE id = :userId")
    fun getUser(userId:Int): User
}