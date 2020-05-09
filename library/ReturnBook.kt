package com.example.library


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.book_return.*


class ReturnBook : AppCompatActivity() {


    private lateinit var returnList:MutableList<BookReturnModel>
    private lateinit var stuAdmission: EditText
    private lateinit var bookName: EditText
    private lateinit var bookId:EditText
    private lateinit var returnDate: EditText
    private lateinit var myRef:DatabaseReference
    private lateinit var listView:ListView
    private lateinit var back:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.book_return)

        stuAdmission=findViewById(R.id.admission_no)
        bookName=findViewById(R.id.book_name)
        bookId=findViewById(R.id.book_id)
        returnDate=findViewById(R.id.return_date)

        listView=findViewById(R.id.list)

        back=findViewById(R.id.back)
        back.setOnClickListener {
            val intent = Intent(this, StudentMainPage::class.java)
            startActivity(intent)
            finish()
        }

        returnList= mutableListOf()
        myRef = FirebaseDatabase.getInstance().getReference("return") //at time to read value from database
        return_btn.setOnClickListener {
            saveInfo()
        }

        myRef.addValueEventListener(object:ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    returnList.clear()
                    for(i in p0.children){
                        val returnBook=i.getValue(BookReturnModel::class.java)
                        returnList.add(returnBook!!)
                    }
                    val adapter=BookReturnAdapter(this@ReturnBook, R.layout.book_return_model,returnList)
                    listView.adapter=adapter
                }
            }

        })
    }

    private fun saveInfo() {
        val admission = stuAdmission.text.toString()
        val bookName = bookName.text.toString()
        val bookId = bookId.text.toString()
        val date=returnDate.text.toString()

        if (bookName.isEmpty() || bookId.isEmpty()||admission.isEmpty()  || date.isEmpty()) {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_LONG).show()
        }
        //  val database : FirebaseDatabase = FirebaseDatabase.getInstance()
        // val myRef :DatabaseReference = database.getReference("Student")
        //   val myRef = FirebaseDatabase.getInstance().getReference("students")   //at time of authentication

        val returnId = myRef.push().key
        val returnBook = returnId?.let { BookReturnModel(it, bookName, bookId, admission,date) }
        if (returnId != null) {
            myRef.child(returnId).setValue(returnBook).addOnCompleteListener {
                Toast.makeText(this, "Book is returned ", Toast.LENGTH_SHORT)
                    .show()
            }
        }


    }

}
