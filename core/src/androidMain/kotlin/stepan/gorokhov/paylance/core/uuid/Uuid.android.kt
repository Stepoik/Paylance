package stepan.gorokhov.paylance.core.uuid

import java.util.UUID

actual class Uuid {
    actual companion object {
        actual fun generate(): String {
            return UUID.randomUUID().toString()
        }
    }
}