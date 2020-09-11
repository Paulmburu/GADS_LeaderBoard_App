package github.paulmburu.gads_leaderboard_app.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/*  
 *  Created by Paul Mburu on 8/1/20.
 *
 *  https://github.com/Paulmburu
 *  Email: paulmburu53@gmail.com
 *         kotdroidsicario@gmail.com
 *  PhoneNumber: +254704002748
 */
fun View.isVisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

fun Context.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

fun showSnackbar(view: View, message: String) {
    Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
}
