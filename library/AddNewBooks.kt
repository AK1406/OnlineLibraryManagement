package com.example.library

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_add_new_books.*
import java.util.*


class AddNewBooks : AppCompatActivity() {

    private lateinit var bookName: EditText
    private lateinit var bookId:EditText
    private lateinit var category:EditText
    private lateinit var location:EditText


    private lateinit var insertBtn:Button
    private lateinit var back: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_books)
        insert_book.setOnClickListener {
            saveInfo()
        }

        bookName=findViewById(R.id.book_name)
        bookId=findViewById(R.id.book_id)
        category=findViewById(R.id.category)
        location=findViewById(R.id.location)

        back=findViewById(R.id.back)
        back.setOnClickListener {
            val intent = Intent(this, AdminMainPage::class.java)
            startActivity(intent)
            finish()
        }
        insertBtn=findViewById(R.id.insert_book)
        insertBtn.setOnClickListener {
            saveInfo()
        }


    }

    private fun saveInfo() {
        val name = bookName.text.toString().trim()
        val bookId = bookId.text.toString()
        val cat = category.text.toString()
        val loc = location.text.toString()

        if (name.isEmpty() || bookId.isEmpty() || cat.isEmpty() || loc.isEmpty()) {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_LONG).show()
        }
        //  val database : FirebaseDatabase = FirebaseDatabase.getInstance()
        // val myRef :DatabaseReference = database.getReference("Student")
           val myRef = FirebaseDatabase.getInstance().getReference("addBooks")   //at time of authentication

        val bid = myRef.push().key
        val addBooks = bid?.let { AddNewBooksModel(it, name, bookId, cat, loc) }
        if (bid != null) {
            myRef.child(bid).setValue(addBooks).addOnCompleteListener {
                Toast.makeText(this, "Book is added successfully", Toast.LENGTH_SHORT)
                    .show()
            }
        }


    }

}
