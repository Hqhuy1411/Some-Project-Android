package com.example.dogapp.viewModel

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.dogapp.R
import com.example.dogapp.model.DogBreed
import com.squareup.picasso.Picasso


class DogsAdapter(var dataSet: ArrayList<DogBreed>) :
    RecyclerView.Adapter<DogsAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val textView2: TextView
        val imageView: ImageView
        init {
            // Define click listener for the ViewHolder's View.
            textView = view.findViewById(R.id.tv_name)
            textView2 = view.findViewById(R.id.tv_origin)
            imageView = view.findViewById(R.id.id_avatar)
            imageView.setOnClickListener { view ->
                val dogBreed = dataSet[adapterPosition]
                val bundle = Bundle()
                bundle.putSerializable("dog", dogBreed)
                Navigation.findNavController(view).navigate(R.id.detailsFragment, bundle)
            }
            view.setOnTouchListener(object :OnSwipeTouchListener(){
                override fun onSwipeLeft() {
                    Log.d("DEBUG", "LEFT")
                    imageView.visibility = View.GONE                }

                override fun onSwipeRight() {
                    Log.d("DEBUG", "RIGHT")
                    imageView.visibility = View.VISIBLE
                }
            })
        }



    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.dog_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textView.text = dataSet[position].name
        viewHolder.textView2.text = dataSet[position].origin
        Picasso.get().load(dataSet[position].url).into(viewHolder.imageView);


    }

    override fun getItemCount()
        = dataSet.size

    fun filterList(filteredlist: ArrayList<DogBreed>) {
        dataSet = filteredlist

        notifyDataSetChanged()
    }
    open class OnSwipeTouchListener() : View.OnTouchListener {

        private val gestureDetector: GestureDetector

        companion object {

            private val SWIPE_THRESHOLD = 100
            private val SWIPE_VELOCITY_THRESHOLD = 100
        }

        init {
            gestureDetector = GestureDetector(GestureListener())
        }

        override fun onTouch(v: View, event: MotionEvent): Boolean {
            return gestureDetector.onTouchEvent(event)
        }

        private inner class GestureListener : GestureDetector.SimpleOnGestureListener() {


            override fun onDown(e: MotionEvent): Boolean {
                return true
            }

            override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
                var result = false
                try {
                    val diffY = e2.y - e1.y
                    val diffX = e2.x - e1.x
                    if (Math.abs(diffX) > Math.abs(diffY)) {
                        if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffX > 0) {
                                onSwipeRight()
                            } else {
                                onSwipeLeft()
                            }
                            result = true
                        }
                    } else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            onSwipeBottom()
                        } else {
                            onSwipeTop()
                        }
                        result = true
                    }
                } catch (exception: Exception) {
                    exception.printStackTrace()
                }

                return result
            }


        }

        open fun onSwipeRight() {}

        open fun onSwipeLeft() {}

        open fun onSwipeTop() {}

        open fun onSwipeBottom() {}
    }

}

