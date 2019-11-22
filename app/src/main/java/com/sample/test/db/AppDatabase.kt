package com.sample.test.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sample.test.db.dao.HomeDao
import com.sample.test.db.entity.Home
import com.sample.test.utils.Constants.DB_NAME

/**
 * Created by SangiliPandian C on 22-11-2019.
 */

@Database(
    entities = [
        Home::class], version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun homeDao(): HomeDao

    companion object {
        private lateinit var DB_INSTANCE: AppDatabase
        fun buildDatabase(context: Context): AppDatabase {
            DB_INSTANCE =
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                )
                    .build()
            return DB_INSTANCE
        }

        fun getInstance(): AppDatabase? {
            return DB_INSTANCE
        }
    }
}
