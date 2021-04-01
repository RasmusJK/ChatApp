import java.io.PrintWriter
import java.net.ServerSocket
import java.util.*

// Opens port so server can be connected to and starts ChatConnector thread
class ChatServer {
    fun serve() {
        val ss = ServerSocket(30000, 2)
        while (true) {
            println("Accepting")
            val s = ss.accept()
            println("Accepted")
            val ins = Scanner(s.getInputStream())
            val out = PrintWriter(s.getOutputStream(), true)
            Thread(ChatConnector(ins, out)).start()

        }
    }
}