package com.example.pizzaapp

import androidx.lifecycle.LiveData
import com.example.pizzaapp.db.PizzaDao
import com.example.pizzaapp.db.Pizzadb
// always interact with database
class PizzaRepository(private val pizzadao: PizzaDao) {
    // on below line we are creating a variable for our list
    // and we are getting all the pizza's from our DAO class.
    val pizzalist: LiveData<List<Pizza>> = pizzadao.getPizza()

    // on below line we are creating an insert method
    // for adding the pizza to our database.
    suspend fun insert(pizza: Pizza) {
        pizzadao.insert_pizza(pizza)
    }
    suspend fun update(pizza: Pizza) {
        pizzadao.update(pizza)
    }


}