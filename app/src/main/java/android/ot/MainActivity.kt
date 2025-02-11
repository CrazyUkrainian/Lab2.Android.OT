package android.ot


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
import android.ot.data.repository.AppSettingsRepository
import android.ot.data.repository.ShiftRepository
import android.ot.data.repository.UserRepository
import android.ot.ui.components.ShiftList
import android.ot.ui.components.ThemeToggleButton
import android.ot.ui.components.UserInfo
import android.ot.ui.theme.MyApplicationTheme
import android.ot.ui.viewmodel.AppSettingsViewModel
import android.ot.ui.viewmodel.AppSettingsViewModelFactory
import android.ot.ui.viewmodel.ShiftViewModel
import android.ot.ui.viewmodel.ShiftViewModelFactory
import android.ot.ui.viewmodel.UserViewModel
import android.ot.ui.viewmodel.UserViewModelFactory


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get the application instance
        val application = application as android.ot.MainActivity

        // Create repository instances
        val userRepository = UserRepository()
        val shiftRepository = ShiftRepository()
        val appSettingsRepository = AppSettingsRepository()

        // Create ViewModel instances using factories
        val userViewModel = ViewModelProvider(
            this,
            UserViewModelFactory(userRepository)
        ).get(UserViewModel::class.java)

        val shiftViewModel = ViewModelProvider(
            this,
            ShiftViewModelFactory(shiftRepository)
        ).get(ShiftViewModel::class.java)

        val appSettingsViewModel = ViewModelProvider(
            this,
            AppSettingsViewModelFactory(appSettingsRepository)
        ).get(AppSettingsViewModel::class.java)

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