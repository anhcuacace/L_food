package com.tunanh.lfood.ativity.activity

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

import com.tunanh.lfood.databinding.ActivityMainBinding
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.tunanh.lfood.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
    private lateinit var googleSignInClient:GoogleSignInClient
    private lateinit var auth:FirebaseAuth
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("998186714367-v2596mt3a1hj7ehg428hj6nkg1qd9oo5.apps.googleusercontent.com")
            .requestEmail()
            .build()

        googleSignInClient= GoogleSignIn.getClient(this,gso)
        binding.googleSignIn.setOnClickListener{
            signIn()
        }


    }

    private fun signIn() {
        val signIntent= googleSignInClient.signInIntent
        startActivityForResult(signIntent, RC_SING_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode== RC_SING_IN){
            val task =GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                //google sign in was successful, authenticate with firebase
                val account=task.getResult(ApiException::class.java)!!
                Log.d(ContentValues.TAG,"FirebaseAuthWithGoogle:"+ account.id)
                firebaseauthWithGoogle(account.idToken!!)
            }catch (e:ApiException){
                // google sign in failed, update iu
                Log.w(ContentValues.TAG,"Google sign in failed",e)
            }
        }

    }
    private fun firebaseauthWithGoogle(idToken: String){
        val credential= GoogleAuthProvider.getCredential(idToken,null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this){ task ->
                if (task.isSuccessful){
                    // sign in success, update UI with the signed- in user's information
                    Log.d(ContentValues.TAG,"signInwithcredential:success")
                    val user= auth.currentUser
                    updateUI(user)
                }else{
                    //if sign in fails, display a message to the user
                    Log.w(ContentValues.TAG,"signinWithCredential:failure",task.exception)
                    updateUI(null)
                }
            }
    }
    private fun updateUI(user:FirebaseUser?){
        if (user!=null){
            val intent= Intent(applicationContext,MainActivity::class.java)
            intent.putExtra(EXTRA_NAME,user.displayName)
            startActivity(intent)
        }
    }
    companion object{
        const val RC_SING_IN=1001
        const val EXTRA_NAME="EXTRA NAME"
    }
}