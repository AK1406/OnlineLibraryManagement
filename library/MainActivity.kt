package com.example.library

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.text.method.MovementMethod
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    private lateinit var studentLogin:TextView
    private lateinit var adminLogin:TextView
    private lateinit var developer:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        developer=findViewById(R.id.creator)
    //   developer.movementMethod(LinkMovementMethod.getInstance())

        studentLogin=findViewById(R.id.student_login)
        adminLogin=findViewById(R.id.admin_login)

        studentLogin.setOnClickListener {
            val intent = Intent(this, LoginActivityStudent::class.java)
            startActivity(intent)
            finish()
        }
        adminLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
