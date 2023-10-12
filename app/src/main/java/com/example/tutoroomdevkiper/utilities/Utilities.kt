package com.example.tutoroomdevkiper.utilities

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
class Utilities {
    companion object {
        fun hideKeyboard(rootView: View) {
            val imm: InputMethodManager =
                rootView.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(rootView.windowToken, 0)
        }
    }
}