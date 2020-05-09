package com.example.library

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.*
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivityStudent : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    lateinit var gso: GoogleSignInOptions
    lateinit var mGoogleSignInClient: GoogleSignInClient
    val RC_SIGN_IN: Int = 1

    private lateinit var emailEt: EditText
    private lateinit var passwordEt: EditText

    private lateinit var signUpBtn: Button
    private lateinit var loginBtn: Button

    private lateinit var hidePass: ImageView
    private lateinit var showPass: ImageView

    private lateinit var resetPasswordTv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_student)

        val signIn = findViewById<View>(R.id.signInBtn) as SignInButton
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        this.mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        signIn.setOnClickListener {
            signIn()
        }

        emailEt = findViewById(R.id.email_edt_text)
        passwordEt = findViewById(R.id.pass_edt_text)

        signUpBtn = findViewById(R.id.signUp_btn)
        loginBtn = findViewById(R.id.login_btn)

        resetPasswordTv = findViewById(R.id.reset_pass_tv)
        hidePass = findViewById(R.id.HideBtn)
        hidePass.visibility = View.VISIBLE
        showPass = findViewById(R.id.showBtn)
        showPass.visibility = View.INVISIBLE

        hidePass.setOnClickListener {
            hidePass.visibility = View.INVISIBLE
            showPass.visibility = View.VISIBLE
            pass_edt_text.transformationMethod = HideReturnsTransformationMethod.getInstance()

        }
        showPass.setOnClickListener {
            showPass.visibility = View.INVISIBLE
            hidePass.visibility = View.VISIBLE
            pass_edt_text.transformationMethod = PasswordTransformationMethod.getInstance()
        }

        auth = FirebaseAuth.getInstance()

        loginBtn.setOnClickListener {
            val email: String = emailEt.text.toString()
            val password: String = passwordEt.text.toString()

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(
                    this@LoginActivityStudent,
                    "Please fill all the fields",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, OnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Successfully Logged In", Toast.LENGTH_LONG).show()
                            val intent = Intent(this, StudentMainPage::class.java)
                            startActivity(intent)
                            finish()

                        } else {
                            Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show()
                        }
                    })
            }
        }

        signUpBtn.setOnClickListener {
            val intent = Intent(this, SignUpActivityStudent::class.java)
            startActivity(intent)
            finish()
        }

        resetPasswordTv.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
    }
    private fun signIn () {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account: GoogleSignInAccount? = task.getResult(ApiException::class.java)
                if (account != null) {
                    val intent =Intent(this,StudentMainPage::class.java)
                }
            } catch (e: ApiException) {
               // Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }

}