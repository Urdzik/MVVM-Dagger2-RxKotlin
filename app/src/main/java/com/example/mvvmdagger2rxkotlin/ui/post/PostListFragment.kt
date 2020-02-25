package com.example.mvvmdagger2rxkotlin.ui.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmdagger2rxkotlin.R
import com.example.mvvmdagger2rxkotlin.databinding.MainFragmentBinding
import com.google.android.material.snackbar.Snackbar


class PostListFragment : Fragment() {

    lateinit var binding: MainFragmentBinding
    lateinit var viewModel: PostListViewModel
    var errorSnackbar: Snackbar? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater)
        binding.postList.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        viewModel = ViewModelProvider(this).get(PostListViewModel::class.java)
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer { error ->
            if (error != null) showError(error) else hideError()

        })





        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }


}