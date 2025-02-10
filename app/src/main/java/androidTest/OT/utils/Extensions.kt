
package androidTest.OT.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidTest.OT.ui.viewmodel.AppSettingsViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


/**
 Useful extensions
 */
fun Long.toFormattedDate(): String {
    val dateFormat = java.text.SimpleDateFormat("yyyy-MM-dd HH:mm", java.util.Locale.getDefault())
    return dateFormat.format(java.util.Date(this * 1000)) // Convert seconds to milliseconds
}


fun String.isValidEmail(): Boolean {
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
    return this.matches(emailRegex.toRegex())
}

fun String.capitalizeFirstLetter(): String {
    return this.replaceFirstChar { it.uppercase() }
}

@Composable
inline fun <reified T : ViewModel> getViewModel(): T {
    return ViewModelProvider(LocalContext.current as androidx.lifecycle.ViewModelStoreOwner)
        .get(T::class.java)
}


@Composable
fun ToggleDarkMode(appSettingsViewModel: AppSettingsViewModel) {
    appSettingsViewModel.toggleDarkMode()
}