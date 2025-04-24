package stepan.gorokhov.paylance.core.uuid

expect class Uuid {
    companion object {
        fun generate(): String
    }
}