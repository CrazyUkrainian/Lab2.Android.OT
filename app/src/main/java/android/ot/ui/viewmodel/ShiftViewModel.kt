package android.ot.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import android.ot.data.repository.ShiftRepository
import android.ot.data.model.Shift
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ShiftViewModel(
    private val shiftRepository: ShiftRepository
) : ViewModel() {

    // Expose shifts as StateFlow with an initial empty list
    val shifts: StateFlow<List<Shift>> = shiftRepository.getShiftsByUserId("1")
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private val _shiftsMutable = MutableStateFlow<List<Shift>>(emptyList())
    val shiftsMutable: StateFlow<List<Shift>> = _shiftsMutable.asStateFlow()

    fun loadShifts(userId: String) {
        viewModelScope.launch {
            _shiftsMutable.value = shiftRepository.getShiftsByUserId(userId).firstOrNull() ?: emptyList()
        }
    }
}
