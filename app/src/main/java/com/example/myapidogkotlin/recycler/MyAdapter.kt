package com.example.myapidogkotlin.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapidogkotlin.R
import com.example.myapidogkotlin.model.Datos

class MyAdapter (private val dataSet: Datos) : RecyclerView.Adapter<MyView>() {

    lateinit var myContexto : Context

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        // Create a new view, which defines the UI of the list item
        myContexto = parent.context
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row, parent, false)

        return MyView(view)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.images!!.size

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyView, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val url : String = dataSet.images!![position]
        Glide.with(myContexto)
            .load(url)
            .into(holder.imV1);
    }
}