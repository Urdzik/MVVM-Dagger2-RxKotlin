package com.example.mvvmdagger2rxkotlin.ui.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mvvmdagger2rxkotlin.dase.BaseViewModel
import com.example.mvvmdagger2rxkotlin.databinding.MainFragmentBinding


class MainFragment : Fragment() {

    lateinit var viewModel: BaseViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = MainFragmentBinding.inflate(inflater)





        return binding.root
    }


}