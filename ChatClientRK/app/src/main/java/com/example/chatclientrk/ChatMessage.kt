package com.example.chatclientrk

import java.time.LocalTime
import java.time.format.DateTimeFormatter
import kotlinx.serialization.*

@Serializable
class ChatMessage(
    val input: String,
    val username: String,
    var timestamp: String = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
) {

    override fun toString(): String {
        return "$username $input $timestamp "
    }
}
