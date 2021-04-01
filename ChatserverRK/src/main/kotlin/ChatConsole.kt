object ChatConsole:Observer {
    override fun newMessage(message: ChatMessage) {
println(message)    }
}