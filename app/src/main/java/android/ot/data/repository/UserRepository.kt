package android.ot.data.repository
import android.ot.data.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepository {

    // Hardcoded list of users for testing
    private val users = listOf(
        User("1", "John Doe", "john@example.com", "123-456-7890"),
        User("2", "Jane Smith", "jane@example.com", "987-654-3210")
    )

    /**
   Fetches a user by their ID.
     */
    fun getUserById(userId: String): Flow<User?> = flow {
        val user = users.find { it.id == userId }
        emit(user)
    }


    fun getAllUsers(): Flow<List<User>> = flow {
        emit(users)
    }
}