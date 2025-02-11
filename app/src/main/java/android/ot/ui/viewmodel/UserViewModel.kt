package android.ot.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import android.ot.data.repository.UserRepository
import android.ot.data.model.User
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class UserViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    // Expose user flow as StateFlow with immediate emission
    val user: StateFlow<User?> = userRepository.getUserById("1")
        .stateIn(viewModelScope, SharingStarted.Lazily, null)

    val allUsers: StateFlow<List<User>> = userRepository.getAllUsers()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private val _userMutable = MutableStateFlow<User?>(null)
    val userMutable: StateFlow<User?> = _userMutable.asStateFlow()

    fun loadUser(userId: String) {
        viewModelScope.launch {
            _userMutable.value = userRepository.getUserById(userId).firstOrNull()
        }
    }
}
