package fitapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import fitapp.model.Exercise
import fitapp.model.WorkoutEntry
import fitapp.model.WorkoutUiState

class WorkoutViewModel : ViewModel() {

    var uiState by mutableStateOf(WorkoutUiState())
        private set

    private var nextEntryId = 0L


    // Derived, synchronously computed from Compose state
    val durationError: String?
        get() = validateDuration(uiState.durationInput)

    fun onDurationChange(value: String) {
        uiState = uiState.copy(durationInput = value)
    }

    fun resetForm() {
        uiState = uiState.copy(durationInput = "")
    }

    fun logWorkout(exercise: Exercise) {
        if (durationError != null) return
        val minutes = uiState.durationInput.trim().toInt()
        val entry = WorkoutEntry(
            id = nextEntryId++,
            exerciseName = exercise.name,
            category = exercise.category,
            minutes = minutes,
            timestamp = System.currentTimeMillis()
        )
        uiState = uiState.copy(
            totalWorkouts = uiState.totalWorkouts + 1,
            totalMinutes = uiState.totalMinutes + minutes,
            history = listOf(entry) + uiState.history, // prepend newest
            durationInput = "",                          // clear field
            showLogDialog = true,
            lastLoggedMinutes = minutes
        )
    }

    fun dismissDialog() {
        uiState = uiState.copy(showLogDialog = false)
    }

    fun clearLogs() {
        uiState = WorkoutUiState()
    }

    companion object {
        fun validateDuration(input: String): String? {
            val text = input.trim()
            if (text.isEmpty()) return "Duration is required"
            val number = text.toIntOrNull() ?: return "Please enter a valid whole number"
            if (number <= 0) return "Duration must be greater than 0"
            return null
        }
    }
}
