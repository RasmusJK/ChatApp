object TopChatter : Observer {
    val chatMap = mutableMapOf<String, Int>()

// couldn't get this working

    override fun newMessage(message: ChatMessage) {
        var i = chatMap[message.username]
        if (i != null) {
            i += 1
            chatMap[message.username] = i
        }
    }

    override fun toString(): String {
        var messagesList = chatMap.toList().sortedBy { (_, value) -> value }.reversed().toMap()
        var topChat = "Top chatters: \n"
        for (i in messagesList) {
            topChat += "User: " + i.key + " sent " + i.value + " messages." + "\n"
        }
        return topChat
    }
}
