import java.sql.DriverManager
import java.time.LocalDate

open class CreateConnection {
    val userName: String = "alt_user"
    val password: String = "password1234"
    val url: String = "jdbc:mysql://localhost:3306/notes"
    val validIDS: MutableList<Int> = mutableListOf()
    open val connection = DriverManager.getConnection(url, userName, password)

    fun setValidIDS() {
        val statement = connection.createStatement()
        val result = statement.executeQuery("SELECT `id` FROM `user_notes` WHERE `deleted` = 0;")
        while (result.next()) {
            val value = result.getInt("id")
            if (value !in validIDS) {
                validIDS.add(value)
            }
        }
    }

    open fun checkValidID(id: Int): Boolean {
        setValidIDS()
        return id in validIDS
    }

    open fun showNotes(all: Boolean) {
        connection.autoCommit = false
        try {
            val statement = connection.createStatement()
            val result =
                if (!all) {
                    statement.executeQuery(
                        "SELECT * FROM `notes`;",
                    )
                } else {
                    statement.executeQuery("SELECT * FROM `user_notes`;")
                }
            println()
            while (result.next()) {
                println("id: ${result.getInt("id")}")
                println("Note content: ${result.getString("content")}")
                println("Category: ${result.getString("category")}")
                println("State: ${result.getString("state")}")
                println("Created: ${result.getDate("created_at")}")
                println("Due on: ${result.getDate("due_date")}")
                if (all) println("Soft deleted: ${result.getInt("deleted")}") else ""
                println()
            }
        } catch (e: Exception) {
            println(e.message)
            e.printStackTrace()
        }
    }

    open fun checkDateValidity(date: String): Boolean {
        val dateToday = LocalDate.now()
        val inputDate = LocalDate.parse(date)
        return inputDate >= dateToday
    }

    open fun printValidIDS() {
        println(validIDS)
    }
}
