package com.example.library


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView



class BookReturnAdapter(private val ctx: Context, private val layoutResId:Int, private val returnList:List<BookReturnModel>)
    :ArrayAdapter<BookReturnModel>(ctx,layoutResId,returnList){

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater:LayoutInflater= LayoutInflater.from(ctx)
        val view:View = layoutInflater.inflate(layoutResId,null)

        val bookView:TextView=view.findViewById(R.id.bookName)
        val bookIDView:TextView=view.findViewById(R.id.bookId)
        val dateView:TextView=view.findViewById(R.id.returnDate)

        val book = returnList[position]

        bookView.text=book.bookName
        bookIDView.text=book.bookId
        dateView.text=book.returnDate
        return view
    }

}