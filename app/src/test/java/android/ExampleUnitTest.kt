package android

import org.junit.Test

import android.ot.data.model.Shift
import android.ot.data.model.User
import android.ot.data.repository.ShiftRepository
import android.ot.data.repository.UserRepository
import android.ot.ui.viewmodel.ShiftViewModel
import android.ot.ui.viewmodel.UserViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before


class ExampleUnitTest {

    private lateinit var userRepository: UserRepository
    private lateinit var shiftRepository: ShiftRepository
    private lateinit var userViewModel: UserViewModel
    private lateinit var shiftViewModel: ShiftViewModel

    @Before
    fun setup() {
        userRepository = UserRepository()
        shiftRepository = ShiftRepository()
        userViewModel = UserViewModel(userRepository)
        shiftViewModel = ShiftViewModel(shiftRepository)
    }

    @Test
    fun testUserRepository() = runBlocking {
        // Fetch a user by ID
        val user = userRepository.getUserById("1").first()
        assertEquals("John Doe", user?.name)
    }

    @Test
    fun testShiftRepository() = runBlocking {
        // Fetch shifts for a user
        val shifts = shiftRepository.getShiftsByUserId("1").first()
        assertEquals(1, shifts.size)
    }


    @Test
    fun testUserViewModel() = runTest {
        userViewModel.loadUser("1")
        advanceUntilIdle() // Ensures coroutine completes
        val user = userViewModel.userMutable.first()
        assertEquals("John Doe", user?.name)
    }

    @Test
    fun testShiftViewModel() = runTest {
        shiftViewModel.loadShifts("1")
        advanceUntilIdle() // Ensures coroutine completes
        val shifts = shiftViewModel.shiftsMutable.first()
        assertEquals(1, shifts.size)
    }


    @Test
    fun testUserModel() {
        // Test User data class
        val user = User("1", "John Doe", "john@example.com", "123-456-7890")
        assertEquals("John Doe", user.name)
    }

    @Test
    fun testShiftModel() {
        // Test Shift data class
        val shift = Shift("1", "1", 1696523400, 1696531200)
        assertEquals("1", shift.userId)
    }
}