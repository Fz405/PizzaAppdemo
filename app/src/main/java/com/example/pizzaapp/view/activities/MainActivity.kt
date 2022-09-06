package com.example.pizzaapp.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.pizzaapp.R
import com.example.pizzaapp.databinding.ActivityMainBinding
import com.example.pizzaapp.view.fragments.PizzaListFragment
import com.example.pizzaapp.view.fragments.WellcomeFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val wellcomeFragment= WellcomeFragment()
        val pizzalistFragment= PizzaListFragment()
        setCurrentFragment(wellcomeFragment)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.tab1 ->setCurrentFragment(wellcomeFragment)
                R.id.tab2 ->setCurrentFragment(pizzalistFragment)
                 }
            true
        }

    }

    private fun setCurrentFragment(fragment:Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            //addToBackStack(null)
            commit()
        }
    override fun onBackPressed() {
        if (binding.bottomNavigationView.selectedItemId == R.id.tab1) {
            super.onBackPressed()
        } else {
            binding.bottomNavigationView.selectedItemId = R.id.tab1
        }
    }

}
