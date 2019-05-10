package scientifik.plotly.server

import hep.dataforge.meta.*
import hep.dataforge.names.Name
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * A change collector that combines all emitted configuration changes until read, than drops all collected changes
 * and starts new batch.
 */
class MetaChangeCollector {
    private val mutex = Mutex()
    private var state = Config()

    suspend fun collect(name: Name, newItem: MetaItem<*>?) {
        mutex.withLock {
            state[name] = newItem
        }
    }

    suspend fun read(): Meta {
        return mutex.withLock {
            state.seal().also {
                state = Config()
            }
        }
    }
}

fun MutableMetaNode<*>.collect(scope: CoroutineScope): MetaChangeCollector {
    val collector = MetaChangeCollector()
    this.onChange(collector) { name, _, item ->
        scope.launch { collector.collect(name, item) }
    }
    return collector
}