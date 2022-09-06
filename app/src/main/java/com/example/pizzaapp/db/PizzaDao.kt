package com.example.pizzaapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.pizzaapp.Pizza

@Dao
interface PizzaDao {
    @Insert()
    suspend fun insert_pizza(pizza:Pizza)
    @Query("Select * from pizza order by id ASC")
    fun getPizza(): LiveData<List<Pizza>>
    @Update
    suspend fun update(note: Pizza)
//    @Delete
//    suspend fun delete(note: Pizza)
}