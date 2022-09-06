package com.example.pizzaapp.view.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.pizzaapp.Pizza
import com.example.pizzaapp.PizzaViewModal
import com.example.pizzaapp.databinding.FragmentAddpizzaBinding

class AddpizzaFragment : DialogFragment() {
    lateinit var viewModal: PizzaViewModal
    private var _binding: FragmentAddpizzaBinding? = null
    private val binding get() = _binding!!
    private val TAG = "com.example.pizzaapp.view.fragments.AddpizzaFragment"


     private lateinit var onClickListener: OnClickListener
        interface OnClickListener {
        fun onDialogPositiveClick(pizza: Pizza? = null)
    }
    companion object
    {
    fun create(onClickListener: OnClickListener): AddpizzaFragment
    {
        return AddpizzaFragment().apply {
            this.onClickListener=onClickListener
        }
    }
}
    override fun onCreateDialog(savedInstanceState: Bundle?):Dialog
    {
        val layoutInflater=LayoutInflater.from(requireContext())
        _binding = FragmentAddpizzaBinding.inflate(layoutInflater)
        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[PizzaViewModal::class.java]
        return activity?.let {
            val builder= AlertDialog.Builder(it)
            builder.setView(binding.root).
                    setPositiveButton("save")
                    {
                        dialog,id ->
                        val pizzas: String = binding.pname.text.toString()
                        val desc: String = binding.pdesc.text.toString()
                        val pizza= Pizza(pizzas, desc)
                        if (pizzas.isNotEmpty() && desc.isNotEmpty()) {
                          viewModal.addPizza(pizza)
                            dialog!!.dismiss()
                             }
                         else
                        {
                            Toast.makeText(activity, "Fields can not be empty", Toast.LENGTH_LONG ).show();
                        }
                        this.onClickListener.onDialogPositiveClick(pizza)
                                            }
            builder.create()
        }?: throw IllegalStateException("Activity can not be null")

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
