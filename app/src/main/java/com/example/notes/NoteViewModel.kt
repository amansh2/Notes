package com.example.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notes.NoteDatabase.Note
import com.example.notes.NoteDatabase.NoteRepository
import com.example.notes.NoteDatabase.NoteRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application):AndroidViewModel(application) {
    lateinit var allNotes:LiveData<List<Note>>
    lateinit var  repository: NoteRepository
    init{
    val dao= NoteRoomDatabase.getDatabase(application).getNoteDao()
    repository= NoteRepository(dao)
    allNotes=repository.allNote
    }

    fun onstart(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(note)
        }
    }

     fun onDelete(note: Note){
         viewModelScope.launch(Dispatchers.IO) {
             repository.delete(note)
         }
    }
}