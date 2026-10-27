package fitapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import fitapp.model.Exercise
import fitapp.model.WorkoutUiState
import fitapp.ui.components.DifficultyBadge

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseDetailScreen(
    exercise: Exercise,
    uiState: WorkoutUiState,
    durationError: String?,
    onDurationChange: (String) -> Unit,
    onLogWorkout: () -> Unit,
    onDismissDialog: () -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(exercise.name) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    exercise.name,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                DifficultyBadge(exercise.difficulty)
            }
            Text(
                "Recommended duration: ${exercise.recommendedMinutes} minutes",
                style = MaterialTheme.typography.bodyMedium
            )

            Text("Instructions", style = MaterialTheme.typography.titleMedium)
            exercise.steps.forEachIndexed { index, step ->
                Row {
                    Text("${index + 1}. ", fontWeight = FontWeight.Bold)
                    Text(step)
                }
            }

            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = uiState.durationInput,
                onValueChange = onDurationChange,
                label = { Text("Duration (Minutes)") },
                singleLine = true,
                isError = durationError != null,
                supportingText = durationError?.let { message ->
                    { Text(message, color = MaterialTheme.colorScheme.error) }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = onLogWorkout,
                enabled = durationError == null,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Log Completed Workout")
            }
        }
    }

    if (uiState.showLogDialog) {
        AlertDialog(
            onDismissRequest = onDismissDialog,
            title = { Text("Workout Logged!") },
            text = {
                Text("${uiState.lastLoggedMinutes} minutes of ${exercise.name} was added to your history.")
            },
            confirmButton = {
                TextButton(onClick = onDismissDialog) { Text("OK") }
            }
        )
    }
}
