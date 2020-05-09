package com.example.library

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.database.FirebaseDatabase


class AddNewBooksAdapter(private val ctx: Context, private val layoutResId:Int, private val bookList:List<AddNewBooksModel>)
    :ArrayAdapter<AddNewBooksModel>(ctx,layoutResId,bookList) {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(ctx)
        val view: View = layoutInflater.inflate(layoutResId, null)

        val bookView: TextView = view.findViewById(R.id.bookName)
        val bookIDView: TextView = view.findViewById(R.id.bookId)
        val categoryView: TextView = view.findViewById(R.id.category)
        val locationView: TextView = view.findViewById(R.id.location)

        val layout:LinearLayout=view.findViewById(R.id.updateLayout)
        val delete:Button=view.findViewById(R.id.delete)


        val book = bookList[position]

        bookView.text = book.bookName
        bookIDView.text = book.bookId
        categoryView.text = book.category
        locationView.text = book.location

        layout.setOnClickListener {
            updateInfo(book)
        }

        delete.setOnClickListener {
            remove(book)
        }

        return view
    }

    private fun updateInfo(book:AddNewBooksModel) {
        val builder= AlertDialog.Builder(ctx)
        val layoutInflater:LayoutInflater= LayoutInflater.from(ctx)
        builder.setTitle("Update Information")

        val view:View = layoutInflater.inflate(R.layout.update_book_details,null)

        val bookNameUpdate:TextView=view.findViewById(R.id.update_book_name)
        val bookIdUpdate:TextView=view.findViewById(R.id.update_book_id)
        val catUpdate:TextView=view.findViewById(R.id.update_category)
        val locUpdate:TextView=view.findViewById(R.id.update_location)

        bookNameUpdate.setText(book.bookName)
        bookIdUpdate.setText(book.bookId)
        catUpdate.setText(book.category)
        locUpdate.setText(book.location)

        builder.setView(view)

        builder.setPositiveButton("Update",object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {

                val upBook= FirebaseDatabase.getInstance().getReference("addBooks")

                val bookName = bookNameUpdate.text.toString().trim()
                val bookId = bookIdUpdate.text.toString()
                val cat = catUpdate.text.toString()
                val loc = locUpdate.text.toString()

                if (bookName.isEmpty()) {
                    bookNameUpdate.error=" Please enter a book name"
                    bookNameUpdate.requestFocus()
                    return
                }
                if (bookId.isEmpty()) {
                    bookIdUpdate.error=" Please enter book id"
                    bookIdUpdate.requestFocus()
                    return
                }

                if (cat.isEmpty()) {
                    catUpdate.error=" Please enter book category"
                    catUpdate.requestFocus()
                    return
                }

                if (loc.isEmpty()) {
                    locUpdate.error=" Please enter book location"
                    locUpdate.requestFocus()
                    return
                }



                val book=AddNewBooksModel(book.id,bookName,bookId,cat,loc)

                upBook.child(book.id).setValue(book)
                Toast.makeText(ctx,"Updated successfully", Toast.LENGTH_SHORT).show()
            }



        })
        builder.setNegativeButton("No",object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {

                val upStudent= FirebaseDatabase.getInstance().getReference("addBooks")

                val bookName = bookNameUpdate.text.toString().trim()
                val bookId = bookIdUpdate.text.toString()
                val cat = catUpdate.text.toString()
                val loc = locUpdate.text.toString()

                val book=AddNewBooksModel(book.id,bookName,bookId,cat,loc)

                upStudent.child(book.id).setValue(book)
                Toast.makeText(ctx,"Information remains as it is", Toast.LENGTH_SHORT).show()
            }


        })
        val alert=builder.create()
        alert.show()
    }


    private fun remove(book:AddNewBooksModel) {

        val addBooks = FirebaseDatabase.getInstance().getReference("addBooks").child(book.id)
        addBooks.removeValue()
        Toast.makeText(ctx, "Book is Removed", Toast.LENGTH_LONG).show()
    }
}