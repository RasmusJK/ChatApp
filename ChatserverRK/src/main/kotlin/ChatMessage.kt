import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlinx.serialization.*
import java.sql.Timestamp
import java.time.LocalTime


@Serializable

class ChatMessage(val input: String, val username: String, var timestamp: String= LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")))  {

    //Adds timestaps to messages
    override fun toString(): String {

        // var parsedInput = Json.parse(ChatMessage.serializer(),input)
        return "$username: $input $timestamp "

    }

   /*  private fun getTime(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        return current.format(formatter)
    }*/
}