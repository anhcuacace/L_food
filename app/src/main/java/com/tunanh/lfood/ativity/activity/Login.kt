package com.tunanh.lfood.ativity.activity

import android.content.ContentValues
import android.content.Context

import android.content.Intent
import android.content.SharedPreferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

import android.util.Log
import android.widget.Toast

import com.facebook.CallbackManager

import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

import com.tunanh.lfood.databinding.ActivityLoginBinding


class Login : AppCompatActivity() {
    private var callbackManager = CallbackManager.Factory.create()
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth

    //    private val startForResult =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
//
//            if (Activity.RESULT_OK == RC_SING_IN) {
//                val task =GoogleSignIn.getSignedInAccountFromIntent(result.data)
//                try {
//                    //google sign in was successful, authenticate with firebase
//                    val account=task.getResult(ApiException::class.java)!!
//                     Log.d(ContentValues.TAG,"FirebaseAuthWithGoogle:"+ account.id)
//                    firebaseauthWithGoogle(account.idToken!!)
//                }catch (e:ApiException){
//                    // google sign in failed, update iu
//                    Log.w(ContentValues.TAG,"Google sign in failed",e)
//                }
//            }
//            callbackManager.onActivityResult(Activity.RESULT_OK,result.resultCode, result.data)
//        }
    private lateinit var binding: ActivityLoginBinding
    var TAG = "Login"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("998186714367-v2596mt3a1hj7ehg428hj6nkg1qd9oo5.apps.googleusercontent.com")
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        binding.googleSignIn.setOnClickListener {
            signInGoogle()
        }
        binding.btnSignin.setOnClickListener {
            signIn()
        }
        auth = Firebase.auth
        // Initialize Facebook Login button


//        LoginManager.getInstance().registerCallback(callbackManager, object :
//            FacebookCallback<LoginResult> {
//            override fun onSuccess(loginResult: LoginResult) {
//                Log.d(TAG, "facebook:onSuccess:$loginResult")
//                handleFacebookAccessToken(loginResult.accessToken)
//            }
//
//            override fun onCancel() {
//                Log.d(TAG, "facebook:onCancel")
//            }
//
//            override fun onError(error: FacebookException) {
//                Log.d(TAG, "facebook:onError", error)
//            }
//        })


//        binding.loginFbButton.setOnClickListener {
//            auth=FirebaseAuth.getInstance()
//            if (userLogin()){
//
//                auth.signOut()
//            }else{
//                LoginManager.getInstance().logInWithReadPermissions(this, listOf("public_profile","email"))
//            }
//        }

//        LoginManager.getInstance().registerCallback(callbackManager, object :
//            FacebookCallback<LoginResult> {
//            override fun onSuccess(loginResult: LoginResult) {
//                Log.d(TAG, "facebook:onSuccess:$loginResult")
//                handleFacebookAccessToken(loginResult.accessToken)
//            }
//
//            override fun onCancel() {
//                Log.d(TAG, "facebook:onCancel")
//            }
//
//            override fun onError(error: FacebookException) {
//                Log.d(TAG, "facebook:onError", error)
//            }
//        })

// login with email password
//        binding.btnSignin

    }

    private fun signIn() {
        val username = binding.usernamelogin.text.toString()
        val password = binding.passwordlogin.text.toString()
        auth.signInWithEmailAndPassword(username, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "email or password fail",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null)
                }
            }
        // [END sign_in_with_email]
    }


//    private fun userLogin(): Boolean {
//        if (auth.currentUser!=null&& AccessToken.getCurrentAccessToken()!!.isExpired){
//            return true
//        }
//        return false
//    }

    private fun signInGoogle() {
        val signIntent = googleSignInClient.signInIntent
        startActivityForResult(signIntent, RC_SING_IN)
//startForResult.launch(signIntent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SING_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                //google sign in was successful, authenticate with firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(ContentValues.TAG, "FirebaseAuthWithGoogle:" + account.id)
                firebaseauthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // google sign in failed, update iu
                Log.w(ContentValues.TAG, "Google sign in failed", e)
            }
        }
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    //    private fun handleFacebookAccessToken(token: AccessToken) {
//        Log.d(TAG, "handleFacebookAccessToken:$token")
//
//        val credential = FacebookAuthProvider.getCredential(token.token)
//        auth.signInWithCredential(credential)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    // Sign in success, update UI with the signed-in user's information
//                    Log.d(TAG, "signInWithCredential:success")
//                    val user = auth.currentUser
//                    updateUI(user)
//                } else {
//                    // If sign in fails, display a message to the user.
//                    Log.w(TAG, "signInWithCredential:failure", task.exception)
//                    Toast.makeText(baseContext, "Authentication failed.",
//                        Toast.LENGTH_SHORT).show()
//                    updateUI(null)
//                }
//            }
//    }
    private fun firebaseauthWithGoogle(idToken: String) {

        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // sign in success, update UI with the signed- in user's information
                    Log.d(ContentValues.TAG, "signInwithcredential:success")
                    val user = auth.currentUser

                    updateUI(user)
                } else {
                    //if sign in fails, display a message to the user
                    Log.w(ContentValues.TAG, "signinWithCredential:failure", task.exception)
                    updateUI(null)
                }
            }
    }


    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.putExtra(EXTRA_NAME, user.displayName)
            val sharedPreferences: SharedPreferences =
                getSharedPreferences("MYPREF", Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor
            editor = sharedPreferences.edit()
            editor.putBoolean("user", true)
            startActivity(intent)
            finish()
        }
    }

    //    override fun onStart() {
//        super.onStart()
//        // Check if user is signed in (non-null) and update UI accordingly.
//        val currentUser= auth.currentUser
//        updateUI(currentUser)
//    }
    private var count = 0
    override fun onBackPressed() {
        count++
        if (count > 1) {
            /* If count is greater than 1 ,you can either move to the next
//        activity or just quit. */
//            val intent = Intent(this, Login::class.java)
//            intent.putExtra("")
//            startActivity(intent)
//            finish()

            /* Quitting */finishAffinity()
        } else {
            Toast.makeText(this, "Press back again to Leave!", Toast.LENGTH_SHORT).show()

            // resetting the counter in 2s
            val handler = Handler(Looper.getMainLooper())

            handler.postDelayed({ count = 0 }, 2000)
        }
        super.onBackPressed()
    }

    companion object {
        const val RC_SING_IN = 1001
        const val EXTRA_NAME = "EXTRA NAME"
    }

}


