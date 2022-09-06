package com.example.pizzaapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pizza")
data class Pizza(
    @ColumnInfo(name = "Name")val Name: String,
    @ColumnInfo(name = "Description")val Desc: String)

    {
        fun setCurrentCount(count: Int) {
           increment=count
        }

        @PrimaryKey(autoGenerate = true) var id = 0
        var increment=0
    }



