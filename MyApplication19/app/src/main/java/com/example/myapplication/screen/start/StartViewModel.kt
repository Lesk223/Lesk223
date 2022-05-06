package com.example.myapplication.screen.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Repostory
import com.example.myapplication.db.NoteDataBase
import com.example.myapplication.db.repostirory.NoteRealisation
import com.example.myapplication.db.repostirory.NoteRepository
import com.example.myapplication.model.NoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StartViewModel(application: Application):AndroidViewModel(application) {
    fun delete (noteModel: NoteModel,onSuccess:()->Unit)=
        viewModelScope.launch (Dispatchers.IO) {
            Repostory.deleteNote(noteModel) {
                onSuccess()
            }
        }
    val context= application
    fun initDatabase(){
val daoNote =NoteDataBase.getInstance(context).getANoteDao()
        Repostory=NoteRealisation(daoNote)
    }
    fun getAllNotes():LiveData<List<NoteModel>>{
        return Repostory.AllNotes

    }
}