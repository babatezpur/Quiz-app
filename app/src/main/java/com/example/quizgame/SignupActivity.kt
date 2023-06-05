package com.example.quizgame

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizgame.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {

    lateinit var signupBinding: ActivitySignupBinding
    val auth : FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        signupBinding = ActivitySignupBinding.inflate(layoutInflater)
        val view = signupBinding.root

        setContentView(view)

        signupBinding.buttonSignup.setOnClickListener {
            val email = signupBinding.editTextSignupEmail.text.toString()
            val password = signupBinding.editTextSignupPassword.text.toString()

            signupWithFirebase(email, password)
        }
    }

    fun signupWithFirebase(email : String , password : String){

        signupBinding.progressBarSignup.visibility = View.VISIBLE
        signupBinding.buttonSignup.isClickable = false

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task->
                if(task.isSuccessful){

                    Toast.makeText(applicationContext,"Account created", Toast.LENGTH_SHORT).show()
                    finish()
                    signupBinding.progressBarSignup.visibility = View.INVISIBLE
                    signupBinding.buttonSignup.isClickable = false

                }else{

                    Toast.makeText(applicationContext,task.exception?.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            }
    }
}