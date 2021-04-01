package com.example.chatclientrk

import com.example.chatclientrk.Messages.messages
import com.example.chatclientrk.Messages.username
import java.io.PrintWriter
import java.net.Socket
import java.util.*
import android.util.Log
import com.example.chatclientrk.Messages.newMsg
import com.example.chatclientrk.Messages.sentMsg
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json

@UnstableDefault
object ServerConnector : Runnable, Observerable {
    val observer = mutableSetOf<Observer>()

    override fun run() {

        val t = Thread(Runnable {

            val s = Socket("192.168.1.118", 30000)
            val ins = Scanner(s.getInputStream())
            val out = PrintWriter(s.getOutputStream(), true)
           //Sends choosen username to server
            out.println(username)

            //after message is received updates RecyclerView
            val insThread = Thread(Runnable {
                while (s.isConnected) {
                    var message = ins.nextLine()
                    messages.add(message)
                    Log.d("msg", "$message")
                    observer.forEach { it.update() }
                }
            })
            insThread.start()
            //After message is sent updates RecyclerView
            val outThread = Thread(Runnable {
                while (s.isConnected) {
                    //Pressing send turn newMsg to true
                    if (newMsg) {

                        newMsg = false
                        val chatMessage = ChatMessage(sentMsg, username)
                        val JsonMessage: String =
                            Json.stringify(ChatMessage.serializer(), chatMessage)
                            //Sends ChatMessage to server as Json.
                            out.println(JsonMessage)
                            observer.forEach { it.update() }
                    }
                }
            })
            outThread.start()

        })
        t.start()

    }

    override fun deregister(who: Observer) {
        observer.remove(who)
    }

    override fun register(who: Observer) {
        observer.add(who)
    }
}




