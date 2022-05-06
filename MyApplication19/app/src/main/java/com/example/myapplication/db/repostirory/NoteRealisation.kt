package com.example.myapplication.db.repostirory

import androidx.lifecycle.LiveData
import com.example.myapplication.db.NoteDao
import com.example.myapplication.model.NoteModel

class NoteRealisation(private val noteDao: NoteDao):NoteRepository {
    override val AllNotes: LiveData<List<NoteModel>>
        get()=noteDao.getAllNotes()

    override suspend fun insertNote(noteModel: NoteModel, onSuccess: () -> Unit) {
noteDao.insert(noteModel)
    onSuccess()
    }

    override suspend fun deleteNote(noteModel: NoteModel, onSuccess: () -> Unit) {
        noteDao.delete(noteModel)
    onSuccess()
    }

}