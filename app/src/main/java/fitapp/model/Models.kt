package fitapp.model

enum class Difficulty { Beginner, Intermediate, Advanced }

data class Category(
    val name: String,
    val description: String
)

data class Exercise(
    val id: String,
    val category: String,
    val name: String,
    val difficulty: Difficulty,
    val preview: String,
    val steps: List<String>,
    val recommendedMinutes: Int
)

data class WorkoutEntry(
    val id: Long,
    val exerciseName: String,
    val category: String,
    val minutes: Int,
    val timestamp: Long
)

data class WorkoutUiState(
    val totalWorkouts: Int = 0,
    val totalMinutes: Int = 0,
    val history: List<WorkoutEntry> = emptyList(),
    val durationInput: String = "",
    val showLogDialog: Boolean = false,
    val lastLoggedMinutes: Int = 0
)
