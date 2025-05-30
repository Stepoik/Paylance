package stepan.gorokhov.viboranet.core.flow

import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DerivedStateFlow<T>(
    private val getValue: () -> T,
    private val flow: Flow<T>,
) : StateFlow<T> {

    override val replayCache: List<T>
        get() = listOf(value)

    override val value: T
        get() = getValue()

    @InternalCoroutinesApi
    override suspend fun collect(collector: FlowCollector<T>): Nothing {
        coroutineScope {
            flow.distinctUntilChanged().stateIn(this).collect(collector)
        }
    }
}

fun <T1, R> StateFlow<T1>.mapState(
    transform: (a: T1) -> R
): StateFlow<R> {
    return DerivedStateFlow(
        getValue = { transform(value) },
        flow = this.map { transform(it) }
    )
}
