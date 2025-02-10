package androidTest.OT.ui.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidTest.OT.data.repository.UserRepository
import androidTest.OT.data.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    // UI State for the current user
    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    // UI State for all users
    private val _allUsers = MutableStateFlow<List<User>>(emptyList())
    val allUsers: StateFlow<List<User>> = _allUsers


    fun loadUser(userId: String) {
        viewModelScope.launch {
            userRepository.getUserById(userId).collect { user ->
                _user.value = user
            }
        }
    }

    fun loadAllUsers() {
        viewModelScope.launch {
            userRepository.getAllUsers().collect { users ->
                _allUsers.value = users
            }
        }
    }
}
