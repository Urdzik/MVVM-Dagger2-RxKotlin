package com.example.mvvmdagger2rxkotlin.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mvvmdagger2rxkotlin.R
import com.example.mvvmdagger2rxkotlin.model.Book
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item.view.*

class Adapter(private val context: Context) : ListAdapter<Book, Adapter.DataViewHolder>(
    DiffCallback()
) {

    private val clickSubject = PublishSubject.create<Book>()
    val clickEvent: PublishSubject<Book> = clickSubject

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(LayoutInflater.from(context).inflate(R.layout.item, parent, false))
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


    inner class DataViewHolder(private val view: View) :
        RecyclerView.ViewHolder(view) {

        fun bind(book: Book) {


            if (book.title != "" && book.title.isNotEmpty()) {
                view.textView.text = book.title
            } else {
                view.textView.text = "error"
            }
            if (book.author != "" && book.author.isNotEmpty()) {
                view.textView2.text = book.author
            } else {
                view.textView2.text = "error"
            }



            Glide
                .with(view.imageView.context)
                .load(book.url)
                .apply(
                    RequestOptions()

                )
                .into(view.imageView)


            //onClick by recycler item
            view.setOnClickListener {
                clickSubject.onNext(book)
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Book>() {
    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem.title == newItem.title
    }

}