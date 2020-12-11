package br.com.rodrigolmti.core_android.test

import androidx.lifecycle.LiveData
import org.junit.Assert
import org.junit.Assert.assertEquals
import java.util.concurrent.CopyOnWriteArrayList

open class AssertStates<T> {

    private val renderEvents: MutableList<T> = CopyOnWriteArrayList()

    fun observeForever(liveData: LiveData<T>) {
        liveData.observeForever { it?.let { renderEvents.add(it) } }
    }

    fun assertStates(vararg expectedStates: T) {
        val eventsCount = renderEvents.size
        val expectedStatesSize = expectedStates.size
        if (expectedStatesSize != eventsCount) {
            Assert.fail(
                """
                |Expected to wait for $expectedStatesSize,
                |but there were $eventsCount Events in total,
                |which is more than expected: ${
                    renderEvents.joinToString(
                        separator = ""
                    ) {
                        "\n${javaClass.simpleName}: $it"
                    }
                }""".trimMargin()
            )
        }

        expectedStates.forEachIndexed { index, expected ->
            val actual = renderEvents.getOrNull(index)
            assertEquals("assert error in position $index of expectedStates", expected, actual)
        }
    }
}
