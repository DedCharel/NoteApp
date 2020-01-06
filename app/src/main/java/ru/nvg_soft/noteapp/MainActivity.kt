package ru.nvg_soft.noteapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.ticket.view.*
import java.util.zip.Inflater

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
