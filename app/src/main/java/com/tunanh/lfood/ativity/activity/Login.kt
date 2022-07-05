package com.tunanh.lfood.ativity.activity

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

import com.tunanh.lfood.databinding.ActivityMainBinding
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.tunanh.lfood.R
import com.tunanh.lfood.databinding.ActivityLoginBinding
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class Login : AppCompatActivity() {
    private var callbackManager= CallbackManager.Factory.create()
    private lateinit var googleSignInClient:GoogleSignInClient
    private lateinit var auth:FirebaseAuth

    private lateinit var binding: ActivityLoginBinding
    var TAG=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken("998186714367-v2596mt3a1hj7ehg428hj6nkg1qd9oo5.apps.googleusercontent.com")
//            .requestEmail()
//            .build()
//
//        googleSignInClient= GoogleSignIn.getClient(this,gso)
//        binding.googleSignIn.setOnClickListener{
//            signIn()
//        }
        // Initialize Facebook Login button



        binding.loginButton.setOnClickListener {
            auth=FirebaseAuth.getInstance()
            if (userLogin()){

                auth.signOut()
            }else{
                LoginManager.getInstance().logInWithReadPermissions(this, listOf("public_profile","email"))
            }
        }

        LoginManager.getInstance().registerCallback(callbackManager, object :
            FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                Log.d(TAG, "facebook:onSuccess:$loginResult")
                handleFacebookAccessToken(loginResult.accessToken)
            }

            override fun onCancel() {
                Log.d(TAG, "facebook:onCancel")
            }

            override fun onError(error: FacebookException) {
                Log.d(TAG, "facebook:onError", error)
            }
        })



    }

    private fun userLogin(): Boolean {
        if (auth.currentUser!=null&& AccessToken.getCurrentAccessToken()!!.isExpired){
            return true
        }
        return false
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
        callbackManager.onActivityResult(requestCode,resultCode, data)
    }
    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d(TAG, "handleFacebookAccessToken:$token")

        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }
    private fun firebaseauthWithGoogle(idToken: String){
        auth = Firebase.auth
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
//    override fun onStart() {
//        super.onStart()
//        // Check if user is signed in (non-null) and update UI accordingly.
//        var currentUser = auth.getCurrentUser()
//        updateUI(currentUser);
//
//    }
    companion object{
        const val RC_SING_IN=1001
        const val EXTRA_NAME="EXTRA NAME"
    }
}