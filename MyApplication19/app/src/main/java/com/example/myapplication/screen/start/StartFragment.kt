package com.example.myapplication.screen.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.APP
import com.example.myapplication.R
import com.example.myapplication.Swipe
import com.example.myapplication.adapter.NoteAdapter
import com.example.myapplication.databinding.FragmentStartBinding
import com.example.myapplication.model.NoteModel

class StartFragment : Fragment() {
    lateinit var binding: FragmentStartBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: NoteAdapter
    lateinit var currentNote:NoteModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this).get(StartViewModel::class.java)
        viewModel.initDatabase()
        recyclerView = binding.recyclerView
        adapter = NoteAdapter()
        recyclerView.adapter = adapter
        viewModel.getAllNotes().observe(viewLifecycleOwner, { listNotes ->
            adapter.setList(listNotes.asReversed())
        })
        binding.floatButton.setOnClickListener {
            APP.navController.navigate(R.id.action_startFragment_to_addNoteFragment)
        }
    }
    companion object {
        fun clickNote(noteModel: NoteModel) {
            val bundle = Bundle()

            bundle.putSerializable("note", noteModel)
            APP.navController.navigate(R.id.action_startFragment_to_detailFragment, bundle)

        }
        fun del(noteModel: NoteModel){
            val viewModel = ViewModelProvider(APP).get(StartViewModel::class.java)
            //viewModel.delete(noteModel){}
            val item =object:Swipe(this,0,ItemTouchHelper.LEFT)
            {
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    super.onSwiped(viewHolder, direction)
                    viewModel.delete(noteModel){}
                }
            }
            val ItemTouchHelper=ItemTouchHelper(item)
            ItemTouchHelper.attachToRecyclerView()
        }

        }//as

}
