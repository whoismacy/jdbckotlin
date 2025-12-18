@Suppress("ktlint:standard:max-line-length")
class DeleteNote : CreateConnection() {
    fun deleteNote(
        id: Int,
        all: Boolean,
    ) {
        try {
            val sqlStatement =
                if (all) {
                    "UPDATE `user_notes` SET `deleted` = 1;"
                } else {
                    "UPDATE `user_notes` SET `deleted` = 1 WHERE `id` = $id;"
                }
            val preparedStatement = connection.prepareStatement(sqlStatement)
            preparedStatement.executeUpdate()
        } catch (e: Exception) {
            println(e.message)
            e.printStackTrace()
            throw Exception("Failed to delete Row")
        }
    }
}
