package android


import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import android.ot.ui.theme.MyApplicationTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun testUserInfoDisplay() {
        // Set up the UI with a sample user
        val user = android.ot.data.model.User("1", "John Doe", "john@example.com", "123-456-7890")

        composeTestRule.setContent {
            MyApplicationTheme {
                android.ot.ui.components.UserInfo(user = user)
            }
        }

        // Verify user information is displayed
        composeTestRule.onNodeWithText("Name: John Doe").assertExists()
        composeTestRule.onNodeWithText("Email: john@example.com").assertExists()
        composeTestRule.onNodeWithText("Phone: 123-456-7890").assertExists()
    }

    @Test
    fun testShiftListDisplay() {
        // Set up the UI with sample shifts
        val shifts = listOf(
            android.ot.data.model.Shift("1", "1", 1696523400, 1696531200),
            android.ot.data.model.Shift("2", "1", 1696609800, 1696617600)
        )

        composeTestRule.setContent {
            MyApplicationTheme {
                android.ot.ui.components.ShiftList(shifts = shifts)
            }
        }

        // Verify shifts are displayed
        composeTestRule.onNodeWithText("Shift ID: 1").assertExists()
        composeTestRule.onNodeWithText("Start Time: 1696523400").assertExists()
        composeTestRule.onNodeWithText("End Time: 1696531200").assertExists()
    }
}