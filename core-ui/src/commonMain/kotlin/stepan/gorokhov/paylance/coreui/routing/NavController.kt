package stepan.gorokhov.paylance.coreui.routing

import androidx.navigation.NavOptionsBuilder

fun NavOptionsBuilder.popUpGraph() {
    popUpTo(0) {
        inclusive = true
    }
}