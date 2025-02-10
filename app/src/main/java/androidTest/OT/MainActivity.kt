package androidTest.OT


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidTest.OT.ui.components.ShiftList
import androidTest.OT.ui.components.ThemeToggleButton
import androidTest.OT.ui.components.UserInfo
import androidTest.OT.ui.theme.MyApplicationTheme
import androidTest.OT.ui.viewmodel.AppSettingsViewModel
import androidTest.OT.ui.viewmodel.ShiftViewModel
import androidTest.OT.ui.viewmodel.UserViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Manually create ViewModel instances
        val userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        val shiftViewModel = ViewModelProvider(this).get(ShiftViewModel::class.java)
        val appSettingsViewModel = ViewModelProvider(this).get(AppSettingsViewModel::class.java)

        setContent {
            // Observe the app settings state
            val isDarkMode by appSettingsViewModel.appSettings.collectAsState()

            // Apply the custom theme
            MyApplicationTheme(darkTheme = isDarkMode.isDarkModeEnabled) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        // Load user and shifts
                        userViewModel.loadUser("1") // Replace with actual user ID
                        shiftViewModel.loadShifts("1") // Replace with actual user ID

                        // Observe user and shifts
                        val user by userViewModel.user.collectAsState()
                        val shifts by shiftViewModel.shifts.collectAsState()

                        // Display user info
                        UserInfo(user = user)

                        // Display shifts
                        ShiftList(shifts = shifts)

                        // Theme toggle button
                        ThemeToggleButton(
                            isDarkMode = isDarkMode.isDarkModeEnabled,
                            onToggle = { appSettingsViewModel.toggleDarkMode() }
                        )
                    }
                }
            }
        }
    }
}
