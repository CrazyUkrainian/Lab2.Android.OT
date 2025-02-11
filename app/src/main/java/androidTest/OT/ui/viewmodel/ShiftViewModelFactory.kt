package androidTest.OT.ui.viewmodel

import androidTest.OT.data.repository.ShiftRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ShiftViewModelFactory(
    private val shiftRepository: ShiftRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShiftViewModel::class.java)) {
            return ShiftViewModel(shiftRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}