package my.anmp.waroengujang.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import my.anmp.waroengujang.data.model.Menu
import my.anmp.waroengujang.data.model.Order
import my.anmp.waroengujang.data.model.OrderMenu
import my.anmp.waroengujang.data.model.User

@Database(entities = [Menu::class,Order::class,OrderMenu::class,User::class], version = 3)
abstract class AppDB:RoomDatabase() {
    abstract fun menuDao(): MenuDAO
    abstract fun orderDao(): OrderDAO
    abstract fun userDao(): UserDao


    companion object {
        val MIGRATION_1_2 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "ALTER TABLE User ADD COLUMN tokenSession VARCHAR(250)"
                )
            }
        }

        private const val Database_NAME = "app.db"

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
                    ).addMigrations(MIGRATION_1_2).fallbackToDestructiveMigrationOnDowngrade().build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}