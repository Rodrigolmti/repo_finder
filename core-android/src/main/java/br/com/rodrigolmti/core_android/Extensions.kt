package br.com.rodrigolmti.core_android

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

private const val DATE_SERVER_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"
private const val DATE_PRESENT_FORMAT = "dd/MM/yyyy"

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun Fragment.hideSoftInput() {
    activity?.hideSoftInput()
}

fun Activity.hideSoftInput() {
    findViewById<View>(android.R.id.content).hideSoftInput()
}

fun View.hideSoftInput() {
    (context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.hideSoftInputFromWindow(
        windowToken,
        0
    )
}

fun Fragment.showSnackBar(
    buttonMessage: String,
    message: String,
    onClick: (_: View) -> Unit
) = view?.let {
    Snackbar.make(it, message, Snackbar.LENGTH_SHORT).setAction(buttonMessage, onClick).show()
}

fun String.formatDate(): String {
    val parser = SimpleDateFormat(DATE_SERVER_FORMAT, Locale.getDefault())
    val formatter = SimpleDateFormat(DATE_PRESENT_FORMAT, Locale.getDefault())
    return parser.parse(this)?.let { formatter.format(it) } ?: ""
}