//Distributes messages to registered observers
interface Observerable {
    fun register(who: Observer)
    fun deregister(who: Observer)
    fun notifyObservers(message: ChatMessage)

}