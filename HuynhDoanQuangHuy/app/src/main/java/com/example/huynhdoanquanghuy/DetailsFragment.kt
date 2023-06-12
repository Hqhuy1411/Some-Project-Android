package com.example.huynhdoanquanghuy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
//import com.example.huynhdoanquanghuy.databinding.FragmentDetailsBinding
import com.example.huynhdoanquanghuy.model.CoffeOrder


class DetailsFragment : Fragment() {

    private var coffe : CoffeOrder ? = null
   // private lateinit var  binding: FragmentDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            coffe = arguments?.getSerializable("coffeOrder") as CoffeOrder
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }


}