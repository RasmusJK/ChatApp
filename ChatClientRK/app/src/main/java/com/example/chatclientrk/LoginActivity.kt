package com.example.chatclientrk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chatclientrk.Messages.username
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.serialization.UnstableDefault

@UnstableDefault
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        button_login.setOnClickListener {
            username = editText_login.text.toString()

            //Choosing username switches to mainActivity
            val intent = Intent(this, MainActivity::class.java).apply{}
            startActivity(intent)
        }
    }
}