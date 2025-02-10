package androidTest.OT.ui.components
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * A button to toggle between light and dark themes.
 * @param isDarkMode Whether dark mode is currently enabled.
 * @param onToggle Called when the button is clicked.
 */
@Composable
fun ThemeToggleButton(
    isDarkMode: Boolean,
    onToggle: () -> Unit
) {
    Button(
        onClick = onToggle,
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = if (isDarkMode) "Switch to Light Mode" else "Switch to Dark Mode")
    }
}