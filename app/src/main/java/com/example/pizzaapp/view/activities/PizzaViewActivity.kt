package com.example.pizzaapp.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.pizzaapp.databinding.ActivityPizzaViewBinding

class PizzaViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPizzaViewBinding
    var noteID = -1;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPizzaViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val pizzaTitle = intent.getStringExtra("Name")
        val pizzaDescription = intent.getStringExtra("Description")
        binding.tvName.text=pizzaTitle
        val bar= supportActionBar
        bar?.title= pizzaTitle
        binding.tvDescription.text= pizzaDescription
        //noteID = intent.getIntExtra("noteId", -1)

    }


    override fun onBackPressed() {
        val fm: FragmentManager = supportFragmentManager
        if (fm.backStackEntryCount > 0) {
            fm.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}