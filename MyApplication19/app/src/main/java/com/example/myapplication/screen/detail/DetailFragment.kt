package com.example.myapplication.screen.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.APP
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDetailBinding
import com.example.myapplication.model.NoteModel
import com.example.myapplication.screen.addnote.AddNoteViewModel


class DetailFragment : Fragment() {
    lateinit var binding: FragmentDetailBinding
    lateinit var currentNote:NoteModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentDetailBinding.inflate(layoutInflater,container,false)
        currentNote=arguments?.getSerializable("note")as NoteModel

return binding.root   }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    init()
    }

    private fun init() {
        val viewModel=ViewModelProvider (this).get(AddNoteViewModel::class.java)
        binding.textViewDescript.text=currentNote.description
        binding.textViewTitle.text=currentNote.title
        binding.delbt.setOnClickListener{
            viewModel.delete(currentNote){}
            APP.navController.navigate(R.id.action_detailFragment_to_startFragment)
        }
    }

}