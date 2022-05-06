package com.example.myapplication.db.repostirory

import androidx.lifecycle.LiveData
import com.example.myapplication.model.NoteModel

interface NoteRepository {
    val AllNotes: LiveData<List<NoteModel>>
    suspend fun insertNote (noteModel: NoteModel,onSuccess:()->Unit)
    suspend fun deleteNote (noteModel: NoteModel,onSuccess:()->Unit)

}