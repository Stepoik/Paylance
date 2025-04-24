package stepan.gorokhov.paylance.coreui.routing

interface BaseRoute {
    val route: String
    val navRoute: String get() = route
}