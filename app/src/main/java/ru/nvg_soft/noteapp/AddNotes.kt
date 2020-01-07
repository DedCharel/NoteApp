package ru.nvg_soft.noteapp

import android.content.ContentValues
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_notes.*

class AddNotes : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)
    }

    fun onClickAdd(view: View){
        var dbManager = DBManager(this)
        var values = ContentValues()
        values.put("Title", etName.text.toString())
        values.put("Description", etDes.text.toString())
        val ID = dbManager.Insert(values)
        if (ID>0){
            Toast.makeText(this," note is added", Toast.LENGTH_LONG).show()
            finish()
        }else{
            Toast.makeText(this," canot add note",Toast.LENGTH_LONG).show()
        }

    }
}
