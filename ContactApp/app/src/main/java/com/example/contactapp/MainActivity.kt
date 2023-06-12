package com.example.contactapp

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding;

    private var contactList : ArrayList<Contact> ? =null
    private var  contactAdapter: ContactAdapter ?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        contactList = ArrayList<Contact>()
        binding.rvContact.layoutManager=LinearLayoutManager(this)
        contactAdapter = ContactAdapter(contactList!!)
        binding.rvContact.adapter = contactAdapter

        contactList!!.add(Contact("A","Nguyen Van","012312","a@gmail.com"))

        contactList!!.add(Contact("B","Van","012312","b@gmail.com"))
        contactAdapter?.notifyDataSetChanged()
        var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val data: Intent? = result.data
                val returnString :Contact? = if (Build.VERSION.SDK_INT >= 33) {

                   data?.getParcelableExtra("send_number",Contact::class.java)
                } else {
                    intent.getParcelableExtra<Contact>("send_number")
                }
                if (returnString != null) {
                    contactList!!.add(returnString)
                }
                contactAdapter?.notifyDataSetChanged()
            }
        }
        binding.btnAdd.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, CreateActivity::class.java)
            resultLauncher.launch(intent)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        // inside inflater we are inflating our menu file.
        menuInflater.inflate(R.menu.example_menu, menu)

        // below line is to get our menu item.
        val searchItem = menu?.findItem(R.id.action_search)

        // getting search view of our item.
        val searchView = searchItem?.actionView as SearchView
//
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query != null) {
                    filter(query)
                }
                return false
            }
        })
        return true
    }
    private fun filter(text: String) {
        // creating a new array list to filter our data.
        val filteredlist: ArrayList<Contact> = ArrayList()

        // running a for loop to compare elements.
        for (item in contactList!!) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.toString().lowercase().contains(text.lowercase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            contactAdapter!!.filterList(filteredlist)
        }
    }



}


