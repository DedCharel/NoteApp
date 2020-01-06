package ru.nvg_soft.noteapp

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SearchView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.ticket.view.*
import java.util.*
import java.util.zip.Inflater
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    var listOfNotes = ArrayList<Note>()
    var adapter:MyNoteAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listOfNotes.add(Note(1,"Встретить жену", "Незабыть встретить жену с поезда 07.01.20 в 4:33  вагон №3, вокзал Ростовский"))
        listOfNotes.add(Note(2,"Покормить собаку", "Покормить этого тупого лохматого зверя"))

        adapter = MyNoteAdapter(listOfNotes)
        lvNotes.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val sv = menu!!.findItem(R.id.app_bar_search).actionView as SearchView
        val sm = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        sv.setSearchableInfo(sm.getSearchableInfo(componentName))
        sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(applicationContext,query,Toast.LENGTH_LONG).show()
                //TODO: search database
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null){
            when (item.itemId){
                R.id.addNote -> {
                    var intent = Intent(this,AddNotes::class.java)
                    startActivity(intent)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
    inner class MyNoteAdapter:BaseAdapter{
        var listNotes = ArrayList<Note>()
        constructor(listNotes:ArrayList<Note>){
            this.listNotes = listNotes
        }
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var myView = layoutInflater.inflate(R.layout.ticket,null)
            var note = listNotes[p0]
            myView.tvTitle.text = note.noteName
            myView.tvContent.text = note.noteDes
            return myView
        }

        override fun getItem(p0: Int): Any {
            return listNotes[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
           return listNotes.size
        }
    }
}
