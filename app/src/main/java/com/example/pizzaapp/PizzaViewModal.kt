package com.example.pizzaapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.pizzaapp.db.Pizzadb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PizzaViewModal(application: Application):AndroidViewModel(application) {
    // on below line I am creating a variable
    // for all pizza's list and repository
    val allPizza : LiveData<List<Pizza>>
    val repository : PizzaRepository
    // on below line I am initializing
    // my dao, repository and all pizza's
    init {
        val dao= Pizzadb.getDatabase(application).pizzadao()
        repository = PizzaRepository(dao)
        allPizza = repository.pizzalist
    }

       // we are calling a method from our repository to add a new pizza.
    fun addPizza(pizza: Pizza) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(pizza)
    }

    fun updatePizza(pizza: Pizza) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(pizza)
    }

}