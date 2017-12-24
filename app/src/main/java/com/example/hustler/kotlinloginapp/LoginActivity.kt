package com.example.hustler.kotlinloginapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.Login
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*


class LoginActivity : AppCompatActivity() {

    private var LoginButton: LoginButton? = null
    private var TextView: TextView? = null

    private var callbackManager: CallbackManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)
        setContentView(R.layout.activity_login)
        var LoginButton = findViewById<Button>(R.id.fb_login)
        var TextView = findViewById<TextView>(R.id.textView)
        callbackManager = CallbackManager.Factory.create()
        //LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"))
        LoginManager.getInstance().registerCallback(callbackManager,
                object : FacebookCallback<LoginResult> {
                    override fun onSuccess(result: LoginResult?) {
                       // Log.d("LoginActivity", "Facebook token: " + result?.accessToken?.token)
                        //startActivity(Intent(applicationContext, MainActivity::class.java))
                        textView.setText("Login Success" + result?.accessToken?.userId)

                    }
                    override fun onCancel() {
                        Log.d("LoginActivity", "Facebook onCancel")
                    }

                    override fun onError(error: FacebookException?) {
                        Log.d("LoginActivity", "Facebook onError")
                    }
                })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //super.onActivityResult(requestCode, resultCode, data)

        callbackManager?.onActivityResult(requestCode,resultCode, data)
    }
}
