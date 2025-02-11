package android.ot.ui.components
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import android.ot.data.model.Shift


@Composable
fun ShiftList(shifts: List<Shift>) {
    if (shifts.isEmpty()) {
        Text("No shifts found.", modifier = Modifier.padding(16.dp))
    } else {
        LazyColumn {
            items(shifts) { shift ->
                ShiftItem(shift = shift)
            }
        }
    }
}

@Composable
fun ShiftItem(shift: Shift) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Shift ID: ${shift.id}")
        Text(text = "Start Time: ${shift.startTime}")
        Text(text = "End Time: ${shift.endTime}")
    }
}