package com.example.ianschoenrock.nestedrecyclerviews

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.example.ianschoenrock.nestedrecyclerviews.Model.Child
import com.example.ianschoenrock.nestedrecyclerviews.Model.DatabaseHandler
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

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        var dbHandler = DatabaseHandler(this)



        if(item != null){
            when(item.itemId){
                R.id.main_add ->{

                    count++
                    val first = First()
                    val child = Child()
                    child.Title = "$count"
                    first.Child = child
                    first.Id = count
                    firstList = dbHandler.allParentListItem
                    dbHandler.addListItem(first)
                    firstAdapter!!.notifyDataSetChanged()
                    loadQuery(first)

                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun loadQuery(first: First){
        var dbHandler = DatabaseHandler(this)

        dbHandler.allParentListItem
//        val projections= arrayOf("Id","Child")
//        val selectionArgs= arrayOf(title)
//        val cursor=dbManager.query(projections,"Child like ?",selectionArgs,"Child")
//        todoList!!.clear()
//        if(cursor.moveToFirst()){
//
//            do{
//                //try writing this with the no constructor in the notes class
//                val ID=cursor.getInt(cursor.getColumnIndex("Id"))
//                val Title=cursor.getString(cursor.getColumnIndex("ListTitle"))
//                val IsChecked = cursor.getInt(cursor.getColumnIndex("IsChecked"))
//
//                todoList!!.add(ToDo(ID,Title, IsChecked))
//
//            }while (cursor.moveToNext())
//        }
    }

}
