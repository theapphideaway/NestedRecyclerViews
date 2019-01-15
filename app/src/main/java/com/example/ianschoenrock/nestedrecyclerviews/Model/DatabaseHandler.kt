package com.example.ianschoenrock.nestedrecyclerviews.Model

import java.lang.reflect.Array.getDouble
import android.database.sqlite.SQLiteDatabase
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast


class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    private val TABLE_LIST = "main_list"
    private val TABLE_LIST_ID = "id"
    private val TABLE_LIST_NAME = "name"

    private val TABLE_CHILD_LIST = "child_list"
    private val TABLE_CHILD_LIST_ID = "id"
    private val TABLE_PARENT_LIST_ID = "parent_id"
    private val TABLE_CHILD_LIST_NAME = "name"
    private val myContext = context


    val allParentListItem: ArrayList<First>
        get() {
            val result = ArrayList<First>()

            val db = this.readableDatabase
            val cursor = db.query(TABLE_LIST, null, null, null, null, null, null)

            while (cursor.moveToNext()) {
                val item = First()
                val child = Child()
                item.Id = (getIntByColumnName(cursor, TABLE_CHILD_LIST_ID))
                item.Child = child
                item.Child!!.Title = (getStringByColumName(cursor, TABLE_LIST_NAME))
                result.add(item)
            }
            cursor.close()
            db.close()
            return result
        }

    override fun onCreate(db: SQLiteDatabase) {

        val CREATE_MAIN_LIST_TABLE = ("CREATE TABLE " + TABLE_LIST + "("
                + TABLE_LIST_ID + " INTEGER PRIMARY KEY,"
                + TABLE_LIST_NAME + " TEXT" + ")")

        val CREATE_TABLE_CHILD_LIST = ("CREATE TABLE " + TABLE_CHILD_LIST + "("
                + TABLE_CHILD_LIST_ID + " INTEGER PRIMARY KEY,"
                + TABLE_PARENT_LIST_ID + " INTEGER,"
                + TABLE_CHILD_LIST_NAME + " TEXT" + ")")

        db.execSQL(CREATE_MAIN_LIST_TABLE)
        db.execSQL(CREATE_TABLE_CHILD_LIST)

        Toast.makeText(myContext, " database is created", Toast.LENGTH_SHORT).show()
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_LIST")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_CHILD_LIST")

        onCreate(db)
    }


    fun addListItem(item: First) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(TABLE_LIST_NAME, item.Child!!.Title)
        db.insert(TABLE_LIST, null, values)
        db.close()
    }

    fun addChildListItem(item: Child) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(TABLE_CHILD_LIST_NAME, item.Title)
        values.put(TABLE_PARENT_LIST_ID, item.Id)
        db.insert(TABLE_LIST, null, values)
        db.close()
    }

    //TODO return the list of child items 
    fun getChildListByParentId(id: Int): ArrayList<Child>{
        var result = ArrayList<Child>()

        return result
    }

    fun deleteParentItem(id: Int) {
        val db = this.writableDatabase
        db.delete(TABLE_LIST, "$TABLE_LIST_ID = ?", arrayOf(id.toString()))
        db.close()
    }

    private fun deleteChildItem(id: Int) {
        val db = this.writableDatabase
        db.delete(TABLE_CHILD_LIST, "$TABLE_CHILD_LIST_ID = ?", arrayOf(id.toString()))
        db.close()
    }


    fun getIntByColumnName(cursor: Cursor, tableColumn: String): Int {
        var result = cursor.getInt(cursor.getColumnIndex(tableColumn))
        return result
    }


    fun getDoubleByColumName(cursor: Cursor, tableColumn: String): Double {
        return cursor.getDouble(cursor.getColumnIndex(tableColumn))
    }


    fun getStringByColumName(cursor: Cursor, tableColumn: String): String {
        return cursor.getString(cursor.getColumnIndex(tableColumn))
    }

    companion object {


        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "List_in_list_name.db" // just use a name.
    }


}