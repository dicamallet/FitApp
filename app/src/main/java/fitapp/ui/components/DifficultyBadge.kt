package fitapp.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import fitapp.model.Difficulty

@Composable
fun DifficultyBadge(difficulty: Difficulty) {
    val (background, content) = when (difficulty) {
        Difficulty.Beginner -> Color(0xFFDCFCE7) to Color(0xFF0F7761)
        Difficulty.Intermediate -> Color(0xFFFEF3C7) to Color(0xFFFF5722)
        Difficulty.Advanced -> Color(0xFFFEE2E2) to Color(0xFF0A4CCE)
    }
    Surface(color = background, shape = RoundedCornerShape(50)) {
        Text(
            text = difficulty.name,
            color = content,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        )
    }
}
