class Update : CreateConnection() {
    fun updateNote(id: Int) {
        val sqlStatement: String = "UPDATE `user_notes` SET `state` = 'complete' WHERE `id` = $id;"
        val preparedStatement = connection.prepareStatement(sqlStatement)
        try {
            preparedStatement.executeUpdate()
        } catch (e: Exception) {
            println(e.message)
            e.printStackTrace()
            throw Error("Failed to update completion state for id: $id")
        }
    }
}
