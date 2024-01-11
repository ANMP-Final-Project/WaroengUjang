package my.anmp.waroengujang.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import my.anmp.waroengujang.data.model.Order
import my.anmp.waroengujang.data.model.OrderMenu

@Dao
interface OrderDAO {
    //parent order
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertOrder(order: Order)

    @Delete
    fun deleteOrder(order: Order)

    @Query("SELECT * FROM `Order`")
    fun getAllOrder(): Flow<List<Order>>

    @Query("SELECT * FROM `Order` ORDER BY id DESC LIMIT 1")
    fun getRecentOrder(): Order

    //menu order
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertOrderMenu(menuOrder: OrderMenu)

    @Update
    fun updateOrderMenu(menuOrder: OrderMenu)

    @Delete
    fun deleteOrderMenu(order: OrderMenu)
    @Query("DELETE FROM OrderMenu Where idOrder = :orderId")
    fun deleteAllOrderMenu(orderId: Int)

    @Query("SELECT * FROM OrderMenu WHERE idOrder = :orderId")
    fun getAllOrderMenu(orderId: Int): List<OrderMenu>
}