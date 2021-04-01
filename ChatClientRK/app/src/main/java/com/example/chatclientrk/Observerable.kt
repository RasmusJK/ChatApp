package com.example.chatclientrk

interface Observerable {
    fun register(who: Observer)
    fun deregister(who: Observer)

}