package com.example.chatclientrk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatclientrk.Messages.messages
import com.example.chatclientrk.Messages.newMsg
import com.example.chatclientrk.Messages.sentMsg
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.serialization.UnstableDefault


@UnstableDefault
class MainActivity : AppCompatActivity(), Observer {
    private lateinit var myMessageAdapter: MessageAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_messages.layoutManager = LinearLayoutManager(this)

        myMessageAdapter = MessageAdapter(messages, this)
        rv_messages.adapter = myMessageAdapter

        ServerConnector.run()
        ServerConnector.register(this)


        button_send.setOnClickListener {
            sentMsg = editText_new.text.toString()
            editText_new.text.clear()
            newMsg = true

        }
    }

    //Prevents going back to login screen.
    override fun onBackPressed() {
        return
    }
    //Makes messages appear in real time
    override fun update() {
        runOnUiThread { myMessageAdapter.notifyDataSetChanged() }
    }

}

