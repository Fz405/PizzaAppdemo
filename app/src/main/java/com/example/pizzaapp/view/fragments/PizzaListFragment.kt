package com.example.pizzaapp.view.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzaapp.*
import com.example.pizzaapp.databinding.FragmentPizzaListBinding
import com.example.pizzaapp.view.activities.PizzaViewActivity


class PizzaListFragment : Fragment() ,PizzaClickInterface,
    AddpizzaFragment.OnClickListener {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentPizzaListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModal: PizzaViewModal
    lateinit var pizzaRV: RecyclerView
    lateinit var pizzaRVAdapter: PizzaAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.invalidateOptionsMenu();
        setHasOptionsMenu(true);
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // val binding = FragmentPizzaListBinding.bind(view)
     //  _binding=binding
        binding.pizzaRV.layoutManager = LinearLayoutManager(view.context,RecyclerView.VERTICAL, false)
        pizzaRVAdapter = PizzaAdapter(activity, this)
        // on below line we are setting
        // adapter to our recycler view.
        binding.pizzaRV.adapter = pizzaRVAdapter
        // on below line we are
        // initializing our view modal.
        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[PizzaViewModal::class.java]

        // on below line we are calling all pizza method
        // from our view modal class to observer the changes on list.
        viewModal.allPizza.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                // on below line we are updating our list.
                pizzaRVAdapter.updateList(it)
                }
        })

        val bar= (activity as AppCompatActivity).supportActionBar
        bar?.title= "Pizzas"
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPizzaListBinding.inflate(inflater, container, false)
         return  binding.root

    }
    override fun onCreateOptionsMenu(
        menu: Menu, inflater: MenuInflater
    ) {
        inflater.inflate(R.menu.addicon, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        if (id == R.id.edit_button) {
            val dialog = AddpizzaFragment.create(this)
            dialog.show(parentFragmentManager,
                "com.example.pizzaapp.view.fragments.AddpizzaFragment"
            )
        }
        //
//      if(id == R.id.action_search){
//          Toast.makeText(getApplicationContext(), "Search action is selected!", Toast.LENGTH_SHORT).show();
//          return true;
//      }
        return super.onOptionsItemSelected(item)
    }

      override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onPizzaClick(pizza: Pizza) {
        // opening a new intent and passing a data to it.
        val intent = Intent(activity, PizzaViewActivity::class.java)
        intent.putExtra("Name", pizza.Name)
        intent.putExtra("Description", pizza.Desc)
        intent.putExtra("noteId", pizza.id)
        startActivity(intent)
         }

    override fun onDialogPositiveClick(pizza: Pizza?) {
        Log.d("PizzaListFragment", "sava button $pizza")
    }




}