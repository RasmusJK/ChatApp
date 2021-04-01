import kotlinx.serialization.json.Json
import java.io.IOException
import java.io.PrintWriter
import java.lang.Exception
import java.util.*

//Connects to Chatserver, Sends messages to chathistory that distribute them back and to other Chatconnectors
class ChatConnector(private val ins: Scanner, private val out: PrintWriter) : Runnable, Observer {

    override fun newMessage(message: ChatMessage) {

        // out.println(JsonMessage)
        out.println(message)
    }

    //login with unique username is required when first connected
    override fun run() {

        val c = ChatConnector(ins, out)

        out.println("Pick a username.")
        var uName = ins.nextLine()

        while (true) {
            if (!Users.nameCheck(uName)) {
                out.println("Name in use, choose another.")
                uName = ins.nextLine()
            } else {
                Users.insert(uName)
                TopChatter.chatMap.putIfAbsent(uName, 0)
                break
            }
        }

        ChatHistory.register(c)
        ChatHistory.insert(ChatMessage("joined the server!", uName))

        while (true) {
            try {
                val line = ins.nextLine()
                // val msg = ChatMessage(line, uName)
                // Commands that can be used with client, commands are not added to ChatHistory
                if (line.contains("/Quit")  || line.contains("/quit")) {
                    //after user disconnect username is freed
                    Users.remove(uName)
                    ChatHistory.insert(ChatMessage("disconnected!", uName))
                    break
                } else if (line.contains("/Help") || line.contains("/help")) {
                    out.println("Commands are: /Quit, /History, /Users and /Top")
                } else if (line.contains("/History") || line.contains("/history")) {
                    out.println(ChatHistory)
                } else if (line.contains("/Users") || line.contains("/users")) {
                    out.println(Users)
                } else if (line.contains("/Top") || line.contains("/top")) {
                    out.println(TopChatter)
                } else {
                    val JsonMessage: ChatMessage = Json.parse(ChatMessage.serializer(), line)
                    // ChatHistory.insert(msg)
                    ChatHistory.insert(JsonMessage)

                }

            } catch (e: Exception) {
                println(e)
                ChatHistory.insert(ChatMessage("disconnected!", uName))
                Users.remove(uName)

                break
            }
        }
    }
}
