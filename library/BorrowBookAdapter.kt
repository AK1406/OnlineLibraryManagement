package com.example.library


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import org.jetbrains.annotations.NotNull


class BorrowBookAdapter(private val ctx: Context, private val layoutResId:Int, private val borrowList:List<BorrowBookModel>)
    :ArrayAdapter<BorrowBookModel>(ctx,layoutResId,borrowList){

    @SuppressLint("ResourceType", "ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater:LayoutInflater= LayoutInflater.from(ctx)
        val view:View = layoutInflater.inflate(layoutResId,null)

        val bookBorrowerName=view.findViewById<TextView>(R.id.borrowerName)
        val bookView=view.findViewById<TextView>(R.id.bookName)
        val bookIDView=view.findViewById<TextView>(R.id.bookId)
        val dateView=view.findViewById<TextView>(R.id.borrowDate)
        val admissionView=view.findViewById<TextView>(R.id.admission)
        val book = borrowList[position]

        bookBorrowerName.text=book.name
        admissionView.text=book.admission
        bookView.text=book.bookName
        bookIDView.text=book.bookId
        dateView.text=book.borrowDate



        return view
    }

}