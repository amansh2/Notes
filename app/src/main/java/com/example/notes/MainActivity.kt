package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes.NoteDatabase.Note
import com.example.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var viewModel:NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       val binding:ActivityMainBinding=DataBindingUtil.setContentView(this,R.layout.activity_main)
      viewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)

        binding.recyclerView.layoutManager=LinearLayoutManager(this)
       val adapter=  NoteRVAdapter(this,this)
       binding.recyclerView.adapter=adapter

        viewModel.allNotes.observe(this, Observer {
            if(it !=null) {
                adapter.updateList(it)
            }
    })

        binding.button2.setOnClickListener {
            if(binding.editText.text.isNotEmpty()){
                viewModel.onstart(Note(binding.editText.text.toString()))
                Toast.makeText(this,"${binding.editText.text} ADDED",Toast.LENGTH_SHORT).show()
            }
        }


    }

    fun onItemClicked(note: Note) {
      viewModel.onDelete(note)
      Toast.makeText(this,"${note.text} DELETED",Toast.LENGTH_SHORT).show()
    }
}