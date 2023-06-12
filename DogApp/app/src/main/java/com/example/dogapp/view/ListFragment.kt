package com.example.dogapp.view

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dogapp.R
import com.example.dogapp.model.DogBreed
import com.example.dogapp.viewModel.DogsAdapter
import com.example.dogapp2.ViewModel.DogsApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers


class ListFragment : Fragment() {

    private lateinit var apiService: DogsApiService
    private var rvDogs: RecyclerView?=null
    private var contactList : ArrayList<DogBreed> ? =null
    private var  contactAdapter: DogsAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

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
        rvDogs = view.findViewById(R.id.rv_dogs)
        contactList = ArrayList<DogBreed>()
        rvDogs?.layoutManager= GridLayoutManager(context,2)
        contactAdapter = DogsAdapter(contactList!!)
        rvDogs!!.adapter = contactAdapter


        apiService = DogsApiService()
        apiService.getDogs()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<DogBreed>>(){
                override fun onError(e: Throwable) {
                    Log.d("DEBUG", "Failure" + e.message)
                }

                override fun onSuccess(t: List<DogBreed>) {not permitted by network security
                    Log.d("DEBUG", "Success")
                    t.forEach{
                        contactList!!.add(it)
                        contactAdapter!!.notifyDataSetChanged()
                    }}

            })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // TODO Add your menu entries here
        super.onCreateOptionsMenu(menu, inflater)

        // inside inflater we are inflating our menu file.
        inflater.inflate(R.menu.menu_search, menu)

        // below line is to get our menu item.
        val searchItem = menu.findItem(R.id.action_search)

        // getting search view of our item.
        val searchView = searchItem.actionView as SearchView
//
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }
//
            override fun onQueryTextChange(query: String?): Boolean {
                if (query != null) {
                   filter(query)
                }
                return false
            }
        })
    }
    private fun filter(text: String) {
        // creating a new array list to filter our data.
        val filteredlist: ArrayList<DogBreed> = ArrayList()

        // running a for loop to compare elements.
        for (item in contactList!!) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.name!!.lowercase().contains(text.lowercase())) {
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
            contactAdapter!!.filterList(filteredlist)
        }
    }
}