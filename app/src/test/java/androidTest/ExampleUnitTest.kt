package androidTest

import org.junit.Test

import androidTest.OT.data.model.Shift
import androidTest.OT.data.model.User
import androidTest.OT.data.repository.ShiftRepository
import androidTest.OT.data.repository.UserRepository
import androidTest.OT.utils.toFormattedDate
import androidTest.OT.ui.viewmodel.ShiftViewModel
import androidTest.OT.ui.viewmodel.UserViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
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
    fun testUserViewModel() = runBlocking {
        // Load and observe user data
        userViewModel.loadUser("1")
        val user = userViewModel.user.first()
        assertEquals("John Doe", user?.name)
    }

    @Test
    fun testShiftViewModel() = runBlocking {
        // Load and observe shift data
        shiftViewModel.loadShifts("1")
        val shifts = shiftViewModel.shifts.first()
        assertEquals(1, shifts.size)
    }

    @Test
    fun testDateFormatting() {
        // Test Unix timestamp formatting
        val timestamp = 1696523400L // 2023-10-05 14:30
        val formattedDate = timestamp.toFormattedDate()
        assertEquals("2023-10-05 14:30", formattedDate)
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