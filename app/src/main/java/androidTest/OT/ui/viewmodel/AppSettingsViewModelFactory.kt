package androidTest.OT.ui.viewmodel

import androidTest.OT.data.repository.AppSettingsRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AppSettingsViewModelFactory(
    private val appSettingsRepository: AppSettingsRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AppSettingsViewModel::class.java)) {
            return AppSettingsViewModel(appSettingsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}