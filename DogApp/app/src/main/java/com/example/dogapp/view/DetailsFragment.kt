package com.example.dogapp.view

import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.dogapp.R
import com.example.dogapp.databinding.FragmentDetailsBinding
import com.example.dogapp.model.DogBreed
import com.squareup.picasso.Picasso


class DetailsFragment : Fragment() {
    private var dog : DogBreed ? = null
    private lateinit var  binding:FragmentDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

            dog = arguments?.getSerializable("dog") as DogBreed

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_details,null,false)
        var root : View = binding.root
        binding.dog = dog
        Picasso.get().load(dog?.url).into(binding.idAvatar);
        return root
        //return inflater.inflate(R.layout.fragment_details, container, false)

    }


}