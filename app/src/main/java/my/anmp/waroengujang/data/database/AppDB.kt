package my.anmp.waroengujang.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import my.anmp.waroengujang.data.model.Menu
import my.anmp.waroengujang.data.model.Order
import my.anmp.waroengujang.data.model.OrderMenu

@Database(entities = [Menu::class,Order::class,OrderMenu::class], version = 1)
abstract class AppDB:RoomDatabase() {
    abstract fun MenuDao(): MenuDAO
    abstract fun orderDao(): OrderDAO

    companion object {
        private const val Database_NAME = "todo.db"

        /**
         * As we need only one instance of db in our app will use to store
         * This is to avoid memory leaks in android when there exist multiple instances of db
         */
        @Volatile
        private var INSTANCE: AppDB? = null

        fun getInstance(context: Context): AppDB {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDB::class.java,
                        Database_NAME
                    ).build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}