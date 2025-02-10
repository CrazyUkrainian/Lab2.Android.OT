package androidTest.OT.ui.viewmodel
import androidTest.OT.data.repository.ShiftRepository
import androidTest.OT.data.model.Shift

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class ShiftViewModel(
    private val shiftRepository: ShiftRepository
) : ViewModel() {

    // UI State for shifts of the current user
    private val _shifts = MutableStateFlow<List<Shift>>(emptyList())
    val shifts: StateFlow<List<Shift>> = _shifts
    fun loadShifts(userId: String) {
        viewModelScope.launch {
            shiftRepository.getShiftsByUserId(userId).collect { shifts ->
                _shifts.value = shifts
            }
        }
    }

    fun loadAllShifts() {
        viewModelScope.launch {
            shiftRepository.getAllShifts().collect { shifts ->
                _shifts.value = shifts
            }
        }
    }
}