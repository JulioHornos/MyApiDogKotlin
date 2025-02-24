package com.example.myapidogkotlin.recycler

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapidogkotlin.R


class MyView(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val imV1 = itemView.findViewById<View>(R.id.imV1) as ImageView
}

