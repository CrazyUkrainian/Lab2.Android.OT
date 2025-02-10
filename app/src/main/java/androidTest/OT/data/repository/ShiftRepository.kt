package androidTest.OT.data.repository
import androidTest.OT.data.model.Shift
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Repository for managing shift data.
 */
class ShiftRepository {

    // Hardcoded list of shifts for testing
    private val shifts = listOf(
        Shift("1", "1", 1672531200, 1672534800), // Shift for user 1
        Shift("2", "2", 1672621200, 1672624800)  // Shift for user 2
    )


    fun getShiftsByUserId(userId: String): Flow<List<Shift>> = flow {
        val userShifts = shifts.filter { it.userId == userId }
        emit(userShifts)
    }


    fun getAllShifts(): Flow<List<Shift>> = flow {
        emit(shifts)
    }
}