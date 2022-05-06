package com.example.myapplication.screen.addnote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Repostory
import com.example.myapplication.model.NoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.annotation.RetentionPolicy

class AddNoteViewModel: ViewModel() {
    fun insert(noteModel: NoteModel,onSuccess:()->Unit)=
        viewModelScope.launch (Dispatchers.IO) {
            Repostory.insertNote(noteModel){
                onSuccess()
            }
        }
    fun delete (noteModel: NoteModel,onSuccess:()->Unit)=
        viewModelScope.launch (Dispatchers.IO) {
            Repostory.deleteNote(noteModel){
                onSuccess()
            }
}}