package ru.nvg_soft.noteapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract
import android.widget.Toast

class DBManager {
    val dbTable = "Notes"
    val colID = "ID"
    val colTitle = "Title"
    val colDes = "Description"
    val dbVersion = 1

    val sqlCrateTable= "CREATE TABLE IF NOT EXIST " + dbTable + " (" + colID + " INTEGER PRIMARY KEY," +
            colTitle + " TEXT, " + colDes + " TEXT);"
    var sqlDB:SQLiteDatabase? = null
    constructor(context: Context) {
        var db = DataBaseHelperNotes(context)
        sqlDB = db.writableDatabase
    }

    inner class DataBaseHelperNotes:SQLiteOpenHelper{
        var context:Context? = null
        constructor(context: Context):super(context,dbTable,null,dbVersion){
            this.context =context
        }
        override fun onCreate(p0: SQLiteDatabase?) {
            p0!!.execSQL(sqlCrateTable)
            Toast.makeText(this.context," database is created",Toast.LENGTH_LONG).show()
        }

        override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
            p0!!.execSQL("Drop table IF EXISTS " + dbTable)
        }

    }
    fun  Insert(values: ContentValues):Long {
        val ID = sqlDB!!.insert(dbTable,"",values)
        return ID
    }
}