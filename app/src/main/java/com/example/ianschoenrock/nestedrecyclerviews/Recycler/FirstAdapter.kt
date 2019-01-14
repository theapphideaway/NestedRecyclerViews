package com.example.ianschoenrock.nestedrecyclerviews.Recycler

import com.example.ianschoenrock.nestedrecyclerviews.ChildActivity
import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.ianschoenrock.nestedrecyclerviews.Model.First
import com.example.ianschoenrock.nestedrecyclerviews.R

class FirstAdapter(private val firstList: ArrayList<First>, private val context: Context):
RecyclerView.Adapter<FirstAdapter.ViewHolder>(){
    override fun onCreateViewHolder(viewGroup: ViewGroup, position : Int): FirstAdapter.ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.card, viewGroup, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return firstList.count()
    }

    override fun onBindViewHolder(holder: FirstAdapter.ViewHolder, position: Int) {
        holder.bindList(firstList[position])

        holder.itemView.setOnClickListener { goToChild(firstList[position], position) }

    }

    fun goToChild(first:First, position:Int){
        var intent = Intent(context, ChildActivity::class.java)
        intent.putExtra("Id", position + 1)
        intent.putExtra("Title", first.Child!!.Title)
        startActivity(context, intent, null)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindList(first: First){

            var itemText: TextView = itemView.findViewById(R.id.main_item) as TextView


            itemText.text = first.Child!!.Title
        }
    }
}