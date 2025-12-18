import java.sql.Date
import java.sql.SQLException

class AddNote : CreateConnection() {
    val sqlStatement: String = "INSERT INTO `user_notes` (`content`, `category`, `due_date`) VALUES (?, ?, ?) ;"
    val preparedStatement = connection.prepareStatement(sqlStatement)

    fun addNote(
        note: String,
        category: String,
        dueDate: String,
    ) {
        try {
            preparedStatement.setString(1, note)
            preparedStatement.setString(2, category)
            preparedStatement.setDate(3, Date.valueOf(dueDate))
            preparedStatement.executeUpdate()
        } catch (e: Exception) {
            println(e.message)
            e.printStackTrace()
            throw SQLException("Failed to Create New Note")
        }
    }
}
