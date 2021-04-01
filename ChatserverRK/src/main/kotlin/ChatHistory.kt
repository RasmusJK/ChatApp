object ChatHistory : Observerable {
    private val observer = mutableSetOf<Observer>()
    private val messageList = mutableListOf<ChatMessage>()
    //sends message to observers
    override fun notifyObservers(message: ChatMessage) {
        for (i in observer) {
            i.newMessage(message)

        }
    }

    override fun deregister(who: Observer) {
        observer.remove(who)
    }

    override fun register(who: Observer) {
        observer.add(who)
    }

    fun insert(message: ChatMessage) {
        messageList.add(message)
        notifyObservers(message)
        TopChatter.newMessage(message)
        ChatConsole.newMessage(message)
    }

    //returns formatted string of all sent messages
    override fun toString(): String {
        var chatMessages = ""
        for (i in messageList) {
            chatMessages += "$i\n\r"

        }
        return chatMessages
    }

}