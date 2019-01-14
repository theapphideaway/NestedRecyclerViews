package com.example.ianschoenrock.nestedrecyclerviews

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.example.ianschoenrock.nestedrecyclerviews.Model.First
import com.example.ianschoenrock.nestedrecyclerviews.Recycler.FirstAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var firstAdapter: FirstAdapter? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var firstList: ArrayList<First>? = null
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstList = ArrayList()
        layoutManager = LinearLayoutManager(this)
        firstAdapter = FirstAdapter(firstList!!, this)

        first_recycler_view.adapter = firstAdapter
        first_recycler_view.layoutManager = layoutManager

        button.setOnClickListener {
            count++
            val first = First()
            first.Title = count.toString()
            firstList!!.add(first)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if(item != null){
            when(item.itemId){
                R.id.main_add ->{

                    count++
                    val first = First()
                    first.Title = count.toString()
                    firstList!!.add(first)
                    firstAdapter!!.notifyDataSetChanged()

                }
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
