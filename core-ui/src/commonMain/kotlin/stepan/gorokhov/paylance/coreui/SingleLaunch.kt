package stepan.gorokhov.paylance.coreui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Composable
fun SingleLaunch(body: suspend () -> Unit) {
    var alreadyRan by rememberSaveable {
        mutableStateOf(false)
    }
    LaunchedEffect(Unit) {
        if (!alreadyRan) {
            alreadyRan = true
            body()
        }
    }
}