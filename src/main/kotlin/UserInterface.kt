class UserInterface : CreateConnection() {
    val deleteNote = DeleteNote()
    val updateNote = Update()
    val createNote = AddNote()

    fun printMenu() {
        println()
        println("MySQL + Kotlin + jdbc Note management app")
        println("FUNCTIONS:")
        println("1. Create a new Note, it's due date, Sort into a Category('self', 'finance', 'entertainment', 'school')")
        println("2. Update a note using it's Id")
        println("3. View notes")
        println("4. Delete note by Id")
        println("5. Delete all your notes")
        println("6. Main Menu")
        println("7. Close Connection")
        println("----------------------------------\nUse Digit")
    }

    fun start() {
        while (true) {
            printMenu()
            val input = Integer.parseInt(readln().trim())
            when (input) {
                1 -> {
                    println("Selected: Create new Note")
                    println("Input the Note's content: ")
                    val noteContent = readln()
                    println("What's the note's category? ('self', 'finance', 'entertainment', 'school')")
                    val noteCategory = readln()
                    println("What's the note's due date? (YYYY-MM-DD)")
                    val noteDate = readln()
                    if (checkDateValidity(noteDate)) {
                        createNote.addNote(note = noteContent, dueDate = noteDate, category = noteCategory)
                    } else {
                        println("Invalid date format")
                        continue
                    }
                    continue
                }

                2 -> {
                    println("Selected: Mark Note as Completed using ID")
                    print("Input Note ID: ")
                    val noteID: Int = Integer.parseInt(readln().trim())
                    if (!deleteNote.checkValidID(noteID)) {
                        println("ID: $noteID does not exist")
                        continue
                    } else {
                        updateNote.updateNote(noteID)
                        println("Marked note as complete")
                    }
                }

                3 -> {
                    println("Selected: View Notes")
                    println("Show All Notes / Show Pending Notes (1/2)")
                    val typeNote = Integer.parseInt(readln()) == 1
                    println("NOTES")
                    println("---------------------------------------------------")
                    createNote.showNotes(typeNote)
                    continue
                }

                4 -> {
                    println("Selected: DELETE NOTE BY ID")
                    print("Input Note ID: ")
                    val deleteNoteId = Integer.parseInt(readln().trim())
                    if (!deleteNote.checkValidID(deleteNoteId)) {
                        println("ID: $deleteNoteId does not exist")
                    } else {
                        deleteNote.deleteNote(deleteNoteId, false)
                        println("Successfully deleted Note")
                        continue
                    }
                }

                5 -> {
                    println("Selected: DELETE ALL NOTES")
                    println("Are you sure you want to delete your notes")
                    val input = readln().trim()
                    if (input.startsWith("y") || input.startsWith("Y")) {
                        deleteNote.deleteNote(0, all = true)
                    }
                    println("Successfully deleted all notes")
                    continue
                }

                6 -> {
                    continue
                }

                7 -> {
                    connection.close()
                    break
                }

                8 -> {
                    deleteNote.printValidIDS()
                }
            }
        }
    }
}
