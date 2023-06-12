package com.example.contactapp2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.getInstance
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.Calendar.getInstance


class ListFragment : Fragment() {

    private var rvContacts: RecyclerView?=null
    private var contactList : ArrayList<Contact> ? =null
    private var  contactAdapter: ContactAdapter?=null
    private var button : FloatingActionButton ? = null
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
        var view :View =  inflater.inflate(R.layout.fragment_list, container, false)

        button = view.findViewById(R.id.button)
        button?.setOnClickListener{
            val fragmentB = AddFragment()
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.list_fragment, fragmentB)
            transaction?.disallowAddToBackStack()
            transaction?.commit()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        button = view.findViewById(R.id.button)

        rvContacts = view.findViewById(R.id.id_rv)
        contactList = ArrayList<Contact>()
        rvContacts?.layoutManager= LinearLayoutManager(context)
        contactAdapter = ContactAdapter(contactList!!)
        rvContacts!!.adapter = contactAdapter

        contactList!!.add(Contact("Nguyen Van A","","",""))
        contactList!!.add(Contact("Nguyen Van B","","",""))
        contactAdapter!!.notifyDataSetChanged()

    }


}