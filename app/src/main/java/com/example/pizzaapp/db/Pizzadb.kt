package com.example.pizzaapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pizzaapp.Pizza

@Database(entities = [Pizza::class], version = 1)
abstract class Pizzadb: RoomDatabase() {
    abstract fun pizzadao(): PizzaDao
     companion object {
         // help to implement singleton
         //private variable which holds database
        private var INSTANCE: Pizzadb? = null
        fun getDatabase(context: Context): Pizzadb {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        Pizzadb::class.java,
                        "pizza_database"
                    )
                        //.createFromAsset("pizza.db")
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}