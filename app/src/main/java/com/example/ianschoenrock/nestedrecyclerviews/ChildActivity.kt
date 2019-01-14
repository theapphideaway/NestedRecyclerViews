package com.example.ianschoenrock.nestedrecyclerviews

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.example.ianschoenrock.nestedrecyclerviews.Model.Child
import com.example.ianschoenrock.nestedrecyclerviews.Model.First
import com.example.ianschoenrock.nestedrecyclerviews.R
import com.example.ianschoenrock.nestedrecyclerviews.Recycler.ChildAdapter
import com.example.ianschoenrock.nestedrecyclerviews.Recycler.FirstAdapter
import kotlinx.android.synthetic.main.activity_child.*
import kotlinx.android.synthetic.main.activity_main.*

class ChildActivity : AppCompatActivity() {

    private var childAdapter: ChildAdapter? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var childList: ArrayList<Child>? = null
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_child)

        var id = 0

        try{
            var bundle:Bundle=intent.extras
            id=bundle.getInt("Id",0)
            if(id!=0) {
                textView.text = bundle.getString("Title")
            }
        }catch (ex:Exception){}

        childList = ArrayList()
        layoutManager = LinearLayoutManager(this)
        childAdapter = ChildAdapter(childList!!, this)

        child_recycler_view.adapter = childAdapter
        child_recycler_view.layoutManager = layoutManager
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.child_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if(item != null){
            when(item.itemId){
                R.id.child_add ->{

                    count++
                    val child = Child()
                    child.Title = count.toString()
                    childList!!.add(child)
                    childAdapter!!.notifyDataSetChanged()

                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

}
