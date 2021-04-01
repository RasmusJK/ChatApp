//Users are managed here
object Users {
    val users = hashSetOf<String>()

    fun insert(who: String) {
        users.add(who)
    }

    fun remove(who: String) {

        users.remove(who)
    }

    //Makes sure that name is not in use
    fun nameCheck(who: String): Boolean {
        return !users.contains(who)

    }

    //Prints string of current users
    override fun toString(): String {
        var userString = ""
        for (i in users) {
            userString += i
            userString += ", "
        }
        return userString
    }

}