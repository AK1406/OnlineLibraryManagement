package com.example.library


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*




class ManageBookDetails : AppCompatActivity() {


    private lateinit var bookList:MutableList<AddNewBooksModel>
    private lateinit var myRef:DatabaseReference
    private lateinit var listView:ListView
    private lateinit var back: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_book_details)


        listView=findViewById(R.id.list)

        bookList= mutableListOf()

        back=findViewById(R.id.back)
        back.setOnClickListener {
            val intent = Intent(this, AdminMainPage::class.java)
            startActivity(intent)
            finish()
        }
        myRef = FirebaseDatabase.getInstance().getReference("addBooks") //at time to read value from database


        myRef.addValueEventListener(object:ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    bookList.clear()
                    for(i in p0.children){
                        val book=i.getValue(AddNewBooksModel::class.java)
                        bookList.add(book!!)
                    }
                    val adapter=AddNewBooksAdapter(this@ManageBookDetails, R.layout.add_new_book_model,bookList)
                    listView.adapter=adapter
                }
            }

        })
    }



}
