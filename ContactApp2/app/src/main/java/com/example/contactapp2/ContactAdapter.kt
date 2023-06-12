package com.example.contactapp2

import android.graphics.Color
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amulyakhare.textdrawable.TextDrawable

class ContactAdapter(var dataSet: ArrayList<Contact>) :
    RecyclerView.Adapter<ContactAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val imageView : ImageView

        init {
            // Define click listener for the ViewHolder's View.
            textView = view.findViewById(R.id.tv_name)
            imageView = view.findViewById(R.id.id_avatar)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
    return ViewHolder(view);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text =dataSet[position].name
        val drawable1 = TextDrawable.builder()
            .beginConfig()
            .width(130)  // width in px
            .height(130) // height in px
            .endConfig()
            // as we are building a circular drawable we
            // are calling a build round method.
            // in that method we are passing our text and color.
            .buildRound(dataSet[position].name?.get(0).toString(), Color.RED);
        holder.imageView.setImageDrawable(drawable1)
    }

    override fun getItemCount(): Int {
        return   dataSet.size
    }

}