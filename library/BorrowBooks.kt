package com.example.library


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.book_borrow.*



class BorrowBooks : AppCompatActivity() {

    private lateinit var borrowList:MutableList<BorrowBookModel>
    private lateinit var stuName: EditText
    private lateinit var stuAdmission: EditText
    private lateinit var bookName: EditText
    private lateinit var bookId:EditText
    private lateinit var borrowDate: EditText
    private lateinit var myRef:DatabaseReference
    private lateinit var listView:ListView
    private lateinit var back: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.book_borrow)


        stuName=findViewById(R.id.edt_name)
        stuAdmission=findViewById(R.id.edt_admission)
        bookName=findViewById(R.id.book_name)
        bookId=findViewById(R.id.book_id)
        borrowDate=findViewById(R.id.borrow_date)

        listView=findViewById(R.id.list)

        borrowList= mutableListOf()

        back=findViewById(R.id.back)
        back.setOnClickListener {
            val intent = Intent(this, StudentMainPage::class.java)
            startActivity(intent)
            finish()
        }
        myRef = FirebaseDatabase.getInstance().getReference("borrow") //at time to read value from database
        borrow_btn.setOnClickListener {
            saveInfo()
        }

        myRef.addValueEventListener(object:ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0!!.exists()){
                    borrowList.clear()
                    for(i in p0.children){
                        val borrowBook=i.getValue(BorrowBookModel::class.java)
                        borrowList.add(borrowBook!!)
                    }
                    val adapter=BorrowBookAdapter(this@BorrowBooks, R.layout.borrow_book_model,borrowList)
                    listView.adapter=adapter
                }
            }

        })
    }

    private fun saveInfo() {
        val name = stuName.text.toString().trim()
        val admission = stuAdmission.text.toString()
        val bookName = bookName.text.toString()
        val bookId = bookId.text.toString()
        val date=borrowDate.text.toString()

        if (name.isEmpty() || admission.isEmpty() || bookName.isEmpty() || bookId.isEmpty() || date.isEmpty()) {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_LONG).show()
        }
        val borrowId = myRef.push().key
        val borrow = borrowId?.let { BorrowBookModel(it, name, admission, bookName, bookId,date) }
        if (borrowId != null) {
            myRef.child(borrowId).setValue(borrow).addOnCompleteListener {
                Toast.makeText(this, "Book is borrowed ", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }

}
