package br.com.rodrigolmti.core_android

import android.text.Editable
import android.text.TextWatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val debounce_time = 500L

class DebounceEditText constructor(
    private val callback: (query: String) -> Unit
) : TextWatcher, CoroutineScope by CoroutineScope(Dispatchers.Main) {

    private var searchFor = ""

    override fun onTextChanged(
        s: CharSequence?,
        start: Int,
        before: Int,
        count: Int,
    ) {
        val searchText = s.toString().trim()
        if (searchText == searchFor)
            return

        searchFor = searchText

        launch {
            if (searchText.isEmpty() || searchText.length < 3)
                return@launch

            delay(debounce_time)

            if (searchText != searchFor)
                return@launch

            callback(searchText)
        }
    }

    override fun afterTextChanged(s: Editable?) = Unit
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
}