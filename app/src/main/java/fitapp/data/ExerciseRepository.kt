package fitapp.data

import fitapp.model.Category
import fitapp.model.Difficulty
import fitapp.model.Exercise

object ExerciseRepository {

    val categories = listOf(
        Category("Strength", "Build muscle & power"),
        Category("Cardio / Endurance", "Boost endurance"),
        Category("Flexibility", "Improve mobility"),
        Category("HIIT", "High-intensity bursts")
    )

    private val exercises = listOf(
        Exercise("str-1", "Strength", "Classic Push-ups", Difficulty.Beginner,
            "Lower your chest to the floor and press back up with a straight body.",
            listOf(
                "Start in a high plank with hands shoulder-width apart.",
                "Brace your core and keep your body in a straight line.",
                "Lower your chest until it nearly touches the floor.",
                "Press back up and repeat for 3 sets of 10-15 reps."
            ), 10),
        Exercise("str-2", "Strength", "Goblet Squats", Difficulty.Intermediate,
            "Hold a weight at your chest and squat deep while keeping your chest tall.",
            listOf(
                "Hold a dumbbell vertically against your chest.",
                "Stand with feet slightly wider than hip-width.",
                "Sit your hips back until thighs are parallel to the floor.",
                "Drive through your heels to stand; 4 sets of 12 reps."
            ), 15),
        Exercise("str-3", "Strength", "Barbell Deadlift", Difficulty.Advanced,
            "Lift a loaded barbell from the floor by driving through your hips.",
            listOf(
                "Stand with the bar over mid-foot.",
                "Hinge and grip the bar just outside your knees.",
                "Flatten your back, brace, and push the floor away.",
                "Lower under control; 5 sets of 5 reps."
            ), 20),
        Exercise("car-1", "Cardio", "Brisk Walking", Difficulty.Beginner,
            "Walk at a quick, steady pace that slightly elevates your breathing.",
            listOf(
                "Warm up with 5 minutes of easy walking.",
                "Increase to a pace where talking is slightly harder.",
                "Maintain the pace for 20 minutes.",
                "Cool down with 5 minutes of slow walking."
            ), 30),
        Exercise("car-2", "Cardio", "Jump Rope", Difficulty.Intermediate,
            "Skip rope rhythmically on the balls of your feet.",
            listOf(
                "Hold handles at hip height with elbows close.",
                "Turn the rope using your wrists.",
                "Jump just high enough to clear the rope.",
                "Alternate 1 minute on, 30 seconds rest for 10 rounds."
            ), 15),
        Exercise("car-3", "Cardio", "Tempo Run", Difficulty.Advanced,
            "Run at a comfortably hard pace for a sustained period.",
            listOf(
                "Jog easily for 10 minutes to warm up.",
                "Increase to a pace you could hold for about an hour.",
                "Hold the tempo pace for 20 minutes.",
                "Cool down with 10 minutes of easy jogging."
            ), 40),
        Exercise("flx-1", "Flexibility", "Hamstring Stretch", Difficulty.Beginner,
            "Gently hinge forward over a straight leg to lengthen the hamstrings.",
            listOf(
                "Sit with one leg extended and the other bent.",
                "Keep your back long and hinge from the hips.",
                "Reach toward your foot and hold for 30 seconds.",
                "Switch legs and repeat 3 times each."
            ), 5),
        Exercise("flx-2", "Flexibility", "Sun Salutation Flow", Difficulty.Intermediate,
            "Move through a linked sequence of yoga poses synced with your breath.",
            listOf(
                "Begin standing in mountain pose.",
                "Inhale arms overhead, exhale and fold forward.",
                "Step back to plank, lower to cobra, press to downward dog.",
                "Step forward and rise; repeat 6 rounds."
            ), 15),
        Exercise("flx-3", "Flexibility", "Pigeon Pose Series", Difficulty.Advanced,
            "Open the hips deeply with progressive pigeon pose variations.",
            listOf(
                "From downward dog, bring one knee behind your wrist.",
                "Extend the back leg straight behind you.",
                "Fold forward over the front shin and hold 1-2 minutes.",
                "Switch sides and repeat."
            ), 20),
        Exercise("hiit-1", "HIIT", "Jumping Jacks Intervals", Difficulty.Beginner,
            "Alternate bursts of jumping jacks with short recovery periods.",
            listOf(
                "Stand tall with feet together.",
                "Jump feet out while raising arms overhead.",
                "Jump back to start and repeat quickly.",
                "Work 30 seconds, rest 30 seconds for 10 rounds."
            ), 10),
        Exercise("hiit-2", "HIIT", "Mountain Climbers", Difficulty.Intermediate,
            "Drive your knees toward your chest rapidly from a plank position.",
            listOf(
                "Start in a high plank position.",
                "Drive your right knee toward your chest.",
                "Switch legs quickly in a running motion.",
                "Work 40 seconds, rest 20 seconds for 12 rounds."
            ), 12),
        Exercise("hiit-3", "HIIT", "Burpee Tabata", Difficulty.Advanced,
            "Perform all-out burpees in 20-second work, 10-second rest cycles.",
            listOf(
                "Squat and place your hands on the floor.",
                "Jump feet back to plank and do a push-up.",
                "Jump feet forward and explode upward.",
                "20 seconds on, 10 seconds off for 8 rounds."
            ), 16)
    )

    fun byCategory(category: String): List<Exercise> =
        exercises.filter { it.category == category }

    fun byId(id: String): Exercise? =
        exercises.find { it.id == id }
}
