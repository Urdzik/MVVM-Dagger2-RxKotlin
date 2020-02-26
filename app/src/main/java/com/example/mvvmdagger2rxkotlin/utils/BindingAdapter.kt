package com.example.mvvmdagger2rxkotlin.utils

import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmdagger2rxkotlin.utils.extension.getParentActivity

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: LiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    visibility?.let {
        parentActivity?.let {
            visibility.observe(
                parentActivity,
                Observer { value -> view.visibility = value ?: View.VISIBLE })
        }
    }
}
