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
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmdagger2rxkotlin.R
import com.example.mvvmdagger2rxkotlin.databinding.MainFragmentBinding
import com.example.mvvmdagger2rxkotlin.dagger.App
import com.example.mvvmdagger2rxkotlin.dagger.module.viewModelFactory.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject


class PostListFragment : Fragment() {

    lateinit var binding: MainFragmentBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: PostListViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var postAdapter: PostListAdapter

    var errorSnackbar: Snackbar? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        App.appComponent.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory).get(PostListViewModel::class.java)
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer { error ->
            if (error != null) showError(error) else hideError()
        })

        binding = MainFragmentBinding.inflate(inflater)
        binding.postList.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        recyclerView = binding.postList

        viewModel.postsLiveData.observe(viewLifecycleOwner, Observer {
            postAdapter = PostListAdapter(it)
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = postAdapter
            }

            postAdapter.notifyDataSetChanged()
        })
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