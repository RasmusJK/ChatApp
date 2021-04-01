import java.io.PrintWriter
import java.util.*

//receives messages from Observerable
interface Observer {

    fun newMessage(message: ChatMessage)
}