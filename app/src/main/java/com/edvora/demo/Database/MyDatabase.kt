package com.ibrahim.spikhairsaloon.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.edvora.demo.Model.ProductDetails
import com.ibrahim.spikhairsaloon.Database.Dao.ProductsDao


@Database(entities = [ProductDetails::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {


    abstract fun paymentDao():ProductsDao

    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getDatabase(context: Context): MyDatabase {
            val instance = INSTANCE
            if (instance != null) {
                return instance
            }

            synchronized(this) {
                val instance = Room
                    .databaseBuilder(context, MyDatabase::class.java, "EDVARO_DEMO")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()

                INSTANCE = instance
                return instance
            }

        }

    }
}