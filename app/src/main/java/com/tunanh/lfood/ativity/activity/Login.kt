package com.tunanh.lfood.ativity.activity

import android.app.Activity
import android.app.Dialog
import android.content.ContentValues

import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils

import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts

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
import com.tunanh.lfood.R

import com.tunanh.lfood.databinding.ActivityLoginBinding


class Login : AppCompatActivity() {
    private var callbackManager= CallbackManager.Factory.create()
    private lateinit var googleSignInClient:GoogleSignInClient
    private lateinit var auth:FirebaseAuth
    private val time_loading:Long = 2000
    private var dialog:Dialog?=null
    private lateinit var binding: ActivityLoginBinding
    private var TAG="Login"
    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (Activity.RESULT_OK == result.resultCode) {
                val task =GoogleSignIn.getSignedInAccountFromIntent(result.data)
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
            callbackManager.onActivityResult(Activity.RESULT_OK,result.resultCode, result.data)
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dialog=Dialog(this)
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            binding.cardView.visibility=View.VISIBLE
            binding.loginLayout.setBackgroundColor(R.color.linh)
            binding.imageView.visibility=View.GONE
            },time_loading)
        logInTransparent()
        logInAccount()
        signUp()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("998186714367-v2596mt3a1hj7ehg428hj6nkg1qd9oo5.apps.googleusercontent.com")
            .requestEmail()
            .build()

        googleSignInClient= GoogleSignIn.getClient(this,gso)

        binding.googleSignIn.setOnClickListener{
            dialog?.show()
            signInGoogle()

        }
        auth = Firebase.auth
    }
    private fun logInAccount() {
        binding.btnLogIn.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val pass = binding.edtPassword.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {
                auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val firebaseUser=auth.currentUser
                        val emails=firebaseUser!!.email
                        Log.d(TAG, "create acc with $emails")
                        updateUI(user = firebaseUser)
                    } else {
                        Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun signUp() {

        binding.btnSignUp.setOnClickListener {
            dialog?.show()
            val emailSignUp = binding.edtEmailSignUp.text.toString()
            val passSignUp = binding.edtPassSignUp.text.toString()
            val rePass = binding.edtRePass.text.toString()




            if (!Patterns.EMAIL_ADDRESS.matcher(emailSignUp).matches()){
                binding.edtEmailSignUp.setError("Invaild email format")
                binding.edtEmailSignUp.requestFocus()
            }else if (TextUtils.isEmpty(passSignUp)){
                binding.edtPassSignUp.setError("password can't be empty")
                binding.edtPassSignUp.requestFocus()
            }else if (passSignUp.length<6){
                binding.edtPassSignUp.setError("password must at least 6 charters long")
                binding.edtPassSignUp.requestFocus()
            }else if (passSignUp.compareTo(rePass)!=0){
                binding.edtRePass.setError("password is not matching")
                binding.edtRePass.requestFocus()
            }else{
                auth.createUserWithEmailAndPassword(emailSignUp, passSignUp).addOnSuccessListener {
                    val firebaseUser=auth.currentUser
                    val emails=firebaseUser!!.email
                    Log.d(TAG, "create acc with $emails")
                    updateUI(user = firebaseUser)

                }.addOnFailureListener {
                    Log.d(TAG, "sign up fail due to ${it.message}")
                }
            }
    }
    }
    private fun logInTransparent() {
        binding.tvSignUp.setOnClickListener {
            binding.tvSignUp.background = resources.getDrawable(R.drawable.switch_trcks, null)
            binding.tvSignUp.setTextColor(resources.getColor(R.color.white, null))
            binding.tvLogIn.background = null
            binding.signUpLayout.visibility = View.VISIBLE
            binding.logInLayout.visibility = View.GONE
            binding.tvLogIn.setTextColor(resources.getColor(R.color.a1, null))
            binding.btnLogIn.visibility = View.GONE
            binding.btnSignUp.visibility = View.VISIBLE
        }
        binding.tvLogIn.setOnClickListener {
            binding.tvLogIn.background = resources.getDrawable(R.drawable.switch_trcks, null)
            binding.tvLogIn.setTextColor(resources.getColor(R.color.white, null))
            binding.tvSignUp.background = null
            binding.logInLayout.visibility = View.VISIBLE
            binding.signUpLayout.visibility = View.GONE
            binding.tvSignUp.setTextColor(resources.getColor(R.color.a1, null))
            binding.btnSignUp.visibility = View.GONE
            binding.btnLogIn.visibility = View.VISIBLE

        }


    }
    private fun signInGoogle() {
        val signIntent= googleSignInClient.signInIntent
//        startActivityForResult(signIntent, RC_SING_IN)
    startForResult.launch(signIntent)
    }


//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode== RC_SING_IN){
//            val task =GoogleSignIn.getSignedInAccountFromIntent(data)
//            try {
//                //google sign in was successful, authenticate with firebase
//                val account=task.getResult(ApiException::class.java)!!
//                Log.d(ContentValues.TAG,"FirebaseAuthWithGoogle:"+ account.id)
//                firebaseauthWithGoogle(account.idToken!!)
//            }catch (e:ApiException){
//                // google sign in failed, update iu
//                Log.w(ContentValues.TAG,"Google sign in failed",e)
//            }
//        }
//        callbackManager.onActivityResult(requestCode,resultCode, data)
//        dialog?.dismiss()
//    }
//
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
        dialog?.dismiss()
        if (user!=null){

            val intent= Intent(applicationContext,MainActivity::class.java)
            intent.putExtra(EXTRA_NAME,user.displayName)
            intent.putExtra(EXTRA_EMAIL,user.email)
            intent.putExtra(IMAGE,user.photoUrl)
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
            finishAffinity()
        } else {
            Toast.makeText(this, "Press back again to Leave!", Toast.LENGTH_SHORT).show()


            val handler = Handler(Looper.getMainLooper())

            handler.postDelayed( { count = 0 }, 2000)
        }
        super.onBackPressed()
    }
    companion object{
//        const val RC_SING_IN=1001
        const val EXTRA_NAME="EXTRA NAME"
        const val EXTRA_EMAIL="EXTRA EMAIL"
        const val IMAGE="EXTRA IMAGE"
    }
}

//private fun handleFacebookAccessToken(token: AccessToken) {
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


//    private fun userLogin(): Boolean {
//        if (auth.currentUser!=null&& AccessToken.getCurrentAccessToken()!!.isExpired){
//            return true
//        }
//        return false
//    }
