package com.example.library

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var emailEt: EditText
    private lateinit var passwordEt: EditText

    private lateinit var hidePass:ImageView
    private lateinit var showPass:ImageView

    private lateinit var loginBtn: Button

    private lateinit var resetPasswordTv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailEt = findViewById(R.id.email_edt_text)
        passwordEt = findViewById(R.id.pass_edt_text)
        hidePass=findViewById(R.id.HideBtn)
        hidePass.visibility= View.VISIBLE
        showPass=findViewById(R.id.showBtn)
        showPass.visibility=View.INVISIBLE

        hidePass.setOnClickListener {
                hidePass.visibility = View.INVISIBLE
                showPass.visibility=View.VISIBLE
                pass_edt_text.transformationMethod = HideReturnsTransformationMethod.getInstance()

            }
        showPass.setOnClickListener{
                showPass.visibility=View.INVISIBLE
                hidePass.visibility=View.VISIBLE
                pass_edt_text.transformationMethod = PasswordTransformationMethod.getInstance()
            }

        loginBtn = findViewById(R.id.login_btn)

        resetPasswordTv = findViewById(R.id.reset_pass_tv)

        auth = FirebaseAuth.getInstance()

        loginBtn.setOnClickListener {
            val email: String = emailEt.text.toString()
            val password: String = passwordEt.text.toString()

            if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(this@LoginActivity, "Please fill all the fields", Toast.LENGTH_LONG).show()
            } else{
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener { task ->
                    if(task.isSuccessful) {
                        Toast.makeText(this, "Successfully Logged In", Toast.LENGTH_LONG).show()
                          val intent = Intent(this, AdminMainPage::class.java)
                           startActivity(intent)
                            finish()

                    }else {
                        Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show()
                    }
                })
            }
        }



        resetPasswordTv.setOnClickListener{
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
    }
}