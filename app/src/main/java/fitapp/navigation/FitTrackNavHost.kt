package fitapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import fitapp.WorkoutViewModel
import fitapp.data.ExerciseRepository
import fitapp.ui.screens.DashboardScreen
import fitapp.ui.screens.ExerciseDetailScreen
import fitapp.ui.screens.ExerciseListScreen

object Routes {
    const val DASHBOARD = "dashboard"
    const val EXERCISE_LIST = "exercises/{category}"
    const val EXERCISE_DETAIL = "exercise/{exerciseId}"

    fun exerciseList(category: String) = "exercises/$category"
    fun exerciseDetail(exerciseId: String) = "exercise/$exerciseId"
}

@Composable
fun FitTrackNavHost(
    // Created ONCE here (Activity scope) and passed to every screen
    viewModel: WorkoutViewModel = viewModel()
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.DASHBOARD) {

        composable(Routes.DASHBOARD) {
            DashboardScreen(
                uiState = viewModel.uiState,
                categories = ExerciseRepository.categories,
                onCategoryClick = { name -> navController.navigate(Routes.exerciseList(name)) },
                onClearLogs = viewModel::clearLogs
            )
        }

        composable(
            route = Routes.EXERCISE_LIST,
            arguments = listOf(navArgument("category") { type = NavType.StringType })
        ) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category").orEmpty()
            ExerciseListScreen(
                category = category,
                exercises = ExerciseRepository.byCategory(category),
                onExerciseClick = { id ->
                    viewModel.resetForm()
                    navController.navigate(Routes.exerciseDetail(id))
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Routes.EXERCISE_DETAIL,
            arguments = listOf(navArgument("exerciseId") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("exerciseId").orEmpty()
            val exercise = ExerciseRepository.byId(id)
            if (exercise != null) {
                ExerciseDetailScreen(
                    exercise = exercise,
                    uiState = viewModel.uiState,
                    durationError = viewModel.durationError,
                    onDurationChange = viewModel::onDurationChange,
                    onLogWorkout = { viewModel.logWorkout(exercise) },
                    onDismissDialog = viewModel::dismissDialog,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}
