package com.example.myapplication.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.NoteModel
import com.example.myapplication.screen.addnote.AddNoteViewModel
import com.example.myapplication.screen.start.StartFragment
import kotlinx.android.synthetic.main.list_item.view.*

class NoteAdapter:RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    var listNote = emptyList<NoteModel>()

    class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {


    }

    override fun getItemCount(): Int {
        return listNote.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.itemView.item_title.text = listNote[position].title
        holder.itemView.dateTime.text = listNote[position].time
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<NoteModel>) {
        listNote = list
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: NoteViewHolder) {
        super.onViewAttachedToWindow(holder)
        StartFragment.del(listNote[holder.adapterPosition])
        holder.itemView.setOnClickListener {


             // StartFragment.del(listNote[holder.adapterPosition])
        }
    }



}
