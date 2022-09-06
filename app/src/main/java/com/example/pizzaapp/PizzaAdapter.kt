package com.example.pizzaapp

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzaapp.databinding.PizzaRvItemBinding

class PizzaAdapter(
    val context: FragmentActivity?,
    val noteClickInterface: PizzaClickInterface
) :
    RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder>() {


    // on below line we are creating a
    // variable for our all notes list.
    private val pizzalist = ArrayList<Pizza>()
    private lateinit var binding: PizzaRvItemBinding;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaViewHolder {
        // inflating our layout file for each item of recycler view.
        binding= PizzaRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PizzaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PizzaViewHolder, position: Int) {
        holder.bind(pizzalist[position])
                // on below line we are setting data to item of recycler view.
        //binding.pizzalst.text = pizzalist[position].Name


        holder.itemView.setOnClickListener {
          noteClickInterface.onPizzaClick(pizzalist[position])
        }
        var count = 0
        binding.increment.setOnClickListener{
            count++
            pizzalist[position].setCurrentCount(count);
            holder.binding.tvincrement.text = count.toString();


        }

    }
    /*fun countincrement(position: Int)
    {    count++
        Log.d("sh", count.toString())
        binding.tvincrement.text= count.toString()

    }*/
    override fun getItemCount(): Int {
       return this.pizzalist.size
    }

    // below method is use to update our list of notes.
    fun updateList(newList: List<Pizza>) {
         pizzalist.clear()
         pizzalist.addAll(newList)
         this.notifyDataSetChanged()
    }
    // on below line we are creating a view holder class.
    class PizzaViewHolder(val binding: PizzaRvItemBinding): RecyclerView.ViewHolder(binding.root)
    {
        fun bind(pizza: Pizza)
        {
            binding.pizzalst.text = pizza.Name
            binding.increment.setImageResource(R.drawable.ic_baseline_add_24)
            binding.tvincrement.text = pizza.increment.toString()

        }
    }
}


interface PizzaClickInterface {
    // creating a method for click action
    // on recycler view item for updating it.
    fun onPizzaClick(pizza: Pizza)
    }


