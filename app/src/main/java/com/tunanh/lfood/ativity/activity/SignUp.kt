package com.tunanh.lfood.ativity.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
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
        val intent=Intent(this,MainActivity::class.java)
        val user = Firebase.auth.currentUser
        user?.let {
            // Name, email address, and profile photo Url
            val name = user.displayName
            val email = user.email
            val photoUrl = user.photoUrl

            // Check if user's email is verified
            val emailVerified = user.isEmailVerified

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            val uid = user.uid
        }
        intent.putExtra("user",user)
        startActivity(intent)
    }


}