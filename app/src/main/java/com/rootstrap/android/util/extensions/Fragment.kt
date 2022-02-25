package com.rootstrap.android.util.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * Allows the collection of an unlimited amount of flows in an inline style without needing to
 * add boilerplate indentations.
 *
 * The flows are all collected inside the same lifecycleState but it can be customized. By default
 * it is Lifecycle.State.STARTED.
 *
 * This extension can be called as many times as needed without problems,
 * but generally you will only require to call it once thanks to the vararg operator.
 *
 * If called with a single Flow Pair the type of the flow is inferred automatically, otherwise
 * you will need to cast the flow values when needed.
 *
 * Example of use : <br>
 * - Single flow collection :
 *      > `collectOnLifeCycle(Pair(viewModel.eventsFlow) { it.collect { event -> onEvents(event) } })`
 * - Multi flow collection :
 *      > Just appending a trailing "," and adding a new Pair allows you to collect an additional
 *          flow per pair
 */
fun <T> Fragment.collectOnLifeCycle(
    vararg flowPairs: Pair<Flow<T>, suspend (Flow<T>) -> Unit>,
    lifecycleState: Lifecycle.State = Lifecycle.State.STARTED
) {
    lifecycleScope.launch {
        repeatOnLifecycle(lifecycleState) {
            flowPairs.forEach {
                launch {
                    it.second(it.first)
                }
            }
        }
    }
}
