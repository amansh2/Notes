package com.example.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.NoteDatabase.Note
import java.util.*


class NoteRVAdapter(private val context: Context, private val listener: MainActivity): RecyclerView.Adapter<NoteRVAdapter.NoteViewHolder>() {

    private val allNotes= ArrayList<Note>()
    inner class NoteViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
     val textView= itemView.findViewById<TextView>(R.id.textView)!!
     val delete= itemView.findViewById<ImageView>(R.id.button)!!

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {

        return NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item, parent, false))
    }


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.textView.text=allNotes[position].text
        holder.delete.setOnClickListener{
            listener.onItemClicked(allNotes[position])
        }


    }
    fun updateList(newList:List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
       return allNotes.size
    }
}