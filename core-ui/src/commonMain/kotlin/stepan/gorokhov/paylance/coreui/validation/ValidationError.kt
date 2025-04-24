package stepan.gorokhov.paylance.coreui.validation

interface ValidationError

sealed class ValidationResult<out T : ValidationError> {
    companion object {
        fun <T : ValidationError> failed(error: T): ValidationResult<T> = FailedResult(error)

        fun <T : ValidationError> success(): ValidationResult<T> = SuccessResult()
    }
}

private data class FailedResult<out T : ValidationError>(
    val error: T
) : ValidationResult<T>()

private class SuccessResult<out T : ValidationError> : ValidationResult<T>()


fun <T : ValidationError> ValidationResult<T>.getErrorOrNull(): T? {
    return (this as? FailedResult<T>)?.error
}

val ValidationResult<*>.isSuccess get() = this is SuccessResult<*>

val ValidationResult<*>.isFailed get() = this is FailedResult<*>