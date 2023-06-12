package com.example.contactapp


import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amulyakhare.textdrawable.TextDrawable

class ContactAdapter(private var dataSet: ArrayList<Contact>) :
    RecyclerView.Adapter<ContactAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val imageView:ImageView
        init {
            // Define click listener for the ViewHolder's View.
            textView = view.findViewById(R.id.tv_name)
            imageView = view.findViewById(R.id.id_avatar)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.contact_row, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val a = dataSet[position].lastName +" "+ dataSet[position].firstName
        viewHolder.textView.text = a
        val drawable1 = TextDrawable.builder()
            .beginConfig()
            .width(130)  // width in px
            .height(130) // height in px
            .endConfig()
            // as we are building a circular drawable we
            // are calling a build round method.
            // in that method we are passing our text and color.
            .buildRound(a[0].toString(), Color.RED);
        viewHolder.imageView.setImageDrawable(drawable1)
    }
    fun filterList(filterer: ArrayList<Contact>) {

        dataSet = filterer

        notifyDataSetChanged()
    }
    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}