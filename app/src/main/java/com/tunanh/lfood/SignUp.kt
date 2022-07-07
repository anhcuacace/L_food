package com.tunanh.lfood

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import androidx.appcompat.app.ActionBar
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.tunanh.lfood.ativity.activity.Login
import com.tunanh.lfood.ativity.activity.MainActivity
import com.tunanh.lfood.databinding.ActivitySignUpBinding

class SignUp : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private var TAG=""
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth= FirebaseAuth.getInstance()
        binding.btnSignup.setOnClickListener { view->
            validateData()
        }
        binding.tvLoginHere.setOnClickListener{view ->
            updateUI()
        }
    }

    private fun validateData() {
        var email:String=binding.usernamesignup.text.toString()
        var password=binding.passwordsignup.text.toString()
        var confirmpass= binding.confirmpasswordsignup.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.usernamesignup.setError("Invaild email format")
            binding.usernamesignup.requestFocus()
        }else if (TextUtils.isEmpty(password)){
            binding.passwordsignup.setError("password can't be empty")
            binding.passwordsignup.requestFocus()
        }else if (password.length<6){
            binding.passwordsignup.setError("password must at least 6 charters long")
            binding.passwordsignup.requestFocus()
        }else if (password.compareTo(confirmpass)!=0){
            binding.confirmpasswordsignup.setError("password is not matching")
            binding.confirmpasswordsignup.requestFocus()
        }else{
            auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                val firebaseUser=auth.currentUser
                val emails=firebaseUser!!.email
                Log.d(TAG, "create acc with $emails")
                updateUI()
                finish()
            }.addOnFailureListener {
                Log.d(TAG, "sign up fail due to ${it.message}")
            }
        }

    }



    private fun updateUI() {
        startActivity(Intent(this,MainActivity::class.java))
    }


}