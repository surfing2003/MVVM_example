package com.example.example_02.Dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.example_02.model.InputMsg

@Database(entities = [InputMsg::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun inputMsgDao(): InputMsgDao
    abstract fun getRequiredTypeConverters(): Map<Class<*>, List<Class<*>>>


    companion object {
        val DB_NAME = "livedata-db"

        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                    }
                }).build()
        }

    }
}
