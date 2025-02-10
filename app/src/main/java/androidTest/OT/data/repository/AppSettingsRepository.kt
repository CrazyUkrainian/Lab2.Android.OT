package androidTest.OT.data.repository
import androidTest.OT.data.model.AppSettings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class AppSettingsRepository {

    // Default app settings (dark mode disabled)
    private val _appSettings = MutableStateFlow(AppSettings(isDarkModeEnabled = false))
    val appSettings: StateFlow<AppSettings> = _appSettings


    fun toggleDarkMode() {
        _appSettings.value = _appSettings.value.copy(
            isDarkModeEnabled = !_appSettings.value.isDarkModeEnabled
        )
    }
}