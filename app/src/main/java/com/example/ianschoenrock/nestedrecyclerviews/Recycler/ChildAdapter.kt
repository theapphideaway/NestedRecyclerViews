package com.example.ianschoenrock.nestedrecyclerviews.Recycler

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.ianschoenrock.nestedrecyclerviews.ChildActivity
import com.example.ianschoenrock.nestedrecyclerviews.Model.Child
import com.example.ianschoenrock.nestedrecyclerviews.Model.First
import com.example.ianschoenrock.nestedrecyclerviews.R

class ChildAdapter(private val childList: ArrayList<Child>, private val context: Context):
    RecyclerView.Adapter<ChildAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ChildAdapter.ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.card, viewGroup, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return childList.count()
    }

    override fun onBindViewHolder(holder: ChildAdapter.ViewHolder, position: Int) {
        holder.bindList(childList[position])
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindList(child: Child) {

            var itemText: TextView = itemView.findViewById(R.id.main_item) as TextView


            itemText.text = child.Title
        }
    }
}