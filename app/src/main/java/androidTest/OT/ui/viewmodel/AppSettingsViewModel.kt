package androidTest.OT.ui.viewmodel
import androidTest.OT.data.repository.AppSettingsRepository
import androidTest.OT.data.model.AppSettings
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for managing app settings.
 */
class AppSettingsViewModel(
    private val appSettingsRepository: AppSettingsRepository
) : ViewModel() {

    // UI State for app settings
    val appSettings: StateFlow<AppSettings> = appSettingsRepository.appSettings

    /**
     * Toggles dark mode on or off.
     */
    fun toggleDarkMode() {
        viewModelScope.launch {
            appSettingsRepository.toggleDarkMode()
        }
    }
}