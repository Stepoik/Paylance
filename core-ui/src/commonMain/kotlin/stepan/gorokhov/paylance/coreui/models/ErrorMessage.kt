package stepan.gorokhov.paylance.coreui.models

import stepan.gorokhov.paylance.core.uuid.Uuid


data class ErrorMessage(
    val message: String,
    val uuid: String = Uuid.generate()
)
