package com.example.library


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class TotalBooksBorrowed : AppCompatActivity() {

    // private  val TAG = "MainActivity"

    private lateinit var borrowList:MutableList<BorrowBookModel>
    private lateinit var myRef:DatabaseReference
    private lateinit var listView:ListView
    private lateinit var back: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.total_books_borrowed)

        listView=findViewById(R.id.list)

        borrowList= mutableListOf()

        back=findViewById(R.id.back)
        back.setOnClickListener {
            val intent = Intent(this, AdminMainPage::class.java)
            startActivity(intent)
            finish()
        }
        myRef = FirebaseDatabase.getInstance().getReference("borrow") //at time to read value from database


        myRef.addValueEventListener(object:ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    borrowList.clear()
                    for(i in p0.children){
                        val borrowBook=i.getValue(BorrowBookModel::class.java)
                        borrowList.add(borrowBook!!)
                    } // made by Anju
                    val adapter=BorrowBookAdapter(this@TotalBooksBorrowed, R.layout.total_book_borrowed_model,borrowList)
                    listView.adapter=adapter
                }
            }

        })
    }

}
