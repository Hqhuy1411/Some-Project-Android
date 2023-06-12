package com.example.huynhdoanquanghuy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.huynhdoanquanghuy.model.CoffeOrder

class CoffeAdapter(var dataSet: ArrayList<CoffeOrder>): RecyclerView.Adapter<CoffeAdapter.ViewHolder>() {

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val textView: TextView
            val textView2: TextView
            val textView3: TextView
            val textView4 : TextView
            val textView5 :TextView
            val textView6 :TextView
            val btnEdit : Button
            val btnDelete : Button
            init {
                // Define click listener for the ViewHolder's View.
                textView = view.findViewById(
                    R.id.tv_oder)
                textView2 = view.findViewById(
                    R.id.tv_whippe)
                textView3 = view.findViewById(R.id.tv_chocolate)
                textView4 = view.findViewById(R.id.tv_price)
                textView5 = view.findViewById(R.id.tv_quantity)
                textView6 = view.findViewById(R.id.tv_status)
                btnEdit = view.findViewById(R.id.btn_edit)
                btnDelete = view.findViewById(R.id.btn_del)
                btnDelete.setOnClickListener { view ->
                    dataSet.removeAt(adapterPosition)
                    notifyDataSetChanged()

                }
                btnEdit.setOnClickListener { view ->
                    val coffeOrder = dataSet[adapterPosition]
                    val bundle = Bundle()
                    bundle.putSerializable("coffeOrder", coffeOrder)
                    Navigation.findNavController(view).navigate(R.id.detailsFragment, bundle)
                }
            }

        }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textView.text = dataSet[position].oder_number.toString()
        viewHolder.textView2.text = dataSet[position].whipped.toString()
        viewHolder.textView3.text = dataSet[position].chocolate.toString()
        viewHolder.textView5.text = dataSet[position].quantity.toString()
        viewHolder.textView4.text = dataSet[position].price.toString()
        viewHolder.textView6.text = dataSet[position].status
        if(dataSet[position].whipped ==true){
            viewHolder.textView2.text = "True"
        }
        if(dataSet[position].chocolate ==true){
            viewHolder.textView3.text = "True"
        }



    }

    override fun getItemCount()
            = dataSet.size

    fun filterList(filteredlist: ArrayList<CoffeOrder>) {
        dataSet = filteredlist

        notifyDataSetChanged()
    }
}