package com.example.starwars.util

import android.view.View
import com.google.android.material.snackbar.Snackbar

object Message {
    fun show(view: View?, message: CharSequence?) {
        Snackbar.make(view!!, message!!, Snackbar.LENGTH_LONG).show()
    }
}