package com.example.huynhdoanquanghuy

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.huynhdoanquanghuy.model.CoffeOrder

class ListFragment : Fragment() {
    private var rv: RecyclerView?=null
    private var coffeList : ArrayList<CoffeOrder> ? =null
    private var  coffeAdapter: CoffeAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv = view.findViewById(R.id.id_rv)
        coffeList = ArrayList<CoffeOrder>()
        rv?.layoutManager= LinearLayoutManager(context)
        coffeAdapter = CoffeAdapter(coffeList!!)
        rv!!.adapter = coffeAdapter

        coffeList!!.add(CoffeOrder(1,
            whippe = true,
            chocolate = false,
            quantity = 2,
            status = "Dang che bien"
        ))
        coffeAdapter!!.notifyDataSetChanged()

        coffeList!!.add(CoffeOrder(2,
            whippe = false,
            chocolate = false,
            quantity = 2,
            status = "Da Phuc vu"
        ))
        coffeAdapter!!.notifyDataSetChanged()

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        // inside inflater we are inflating our menu file.
        inflater.inflate(R.menu.menu, menu)

        // below line is to get our menu item.
        val searchItem = menu.findItem(R.id.action_search)

        // getting search view of our item.
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }
            //
            override fun onQueryTextChange(query: String?): Boolean {
                if (query != null) {
                    //filter(query)
                }
                return false
            }
        })
    }
    private fun filter(text: String) {
        // creating a new array list to filter our data.
        val filteredlist: ArrayList<CoffeOrder> = ArrayList()

        // running a for loop to compare elements.
        for (item in coffeList!!) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.status!!.lowercase().contains(text.lowercase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            coffeAdapter!!.filterList(filteredlist)
        }
    }

}