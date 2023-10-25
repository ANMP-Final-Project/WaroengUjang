package my.anmp.waroengujang.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun shortToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun longToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun shortSnackBar(context: Context, rootView: View, message: String) {
    Snackbar.make(context, rootView, message, Snackbar.LENGTH_SHORT).show()
}

fun longSnackBar(context: Context, rootView: View, message: String) {
    Snackbar.make(context, rootView, message, Snackbar.LENGTH_LONG).show()
}

fun loadImage(context: Context, url: String, imageView: ImageView) {
    Glide.with(context).load(url).centerCrop().into(imageView)
}

fun showAlert(
    context: Context,
    title: String = "Alert",
    msg: String,
    positiveButton: String = "Ok",
    onConfirm: (MaterialDialog) -> Unit = { it.dismiss() }
) {
    MaterialDialog(context).show {
        title(text = title)
        message(text = msg)
        positiveButton(text = positiveButton) {
            onConfirm(it)
        }
    }
}