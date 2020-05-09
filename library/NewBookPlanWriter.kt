package com.example.library

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

class NewBookPlanWriter : AppCompatActivity() {
    private lateinit var back:Button
    private lateinit var msg:EditText
    private lateinit var submitBtn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_book_plan_writer)

        submitBtn=findViewById(R.id.submit)
        msg=findViewById(R.id.textMessage)
        back=findViewById(R.id.back)
        back.setOnClickListener {
            val intent = Intent(this, AdminMainPage::class.java)
            startActivity(intent)
            finish()
        }

        submitBtn.setOnClickListener {
            saveInfo()
        }
    }

    private fun saveInfo() {
        val msgText=msg.text.toString().trim()

        if (msgText.isEmpty() ) {
            Toast.makeText(this, "Please enter your message", Toast.LENGTH_LONG).show()
        }
        val myRef = FirebaseDatabase.getInstance().getReference("BookPlan")
        val msgId = myRef.push().key
        val message = msgId?.let { NewBookPlanModel(it,msgText) }
        if (msgId != null) {
            myRef.child(msgId).setValue(message).addOnCompleteListener {
                Toast.makeText(this, "Plan is saved successfully ", Toast.LENGTH_SHORT)
                    .show()
            }
        }


    }
}

