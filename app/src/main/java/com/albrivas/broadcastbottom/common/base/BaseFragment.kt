package com.albrivas.broadcastbottom.common.base

import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import com.albrivas.broadcastbottom.R
import com.albrivas.broadcastbottom.ui.base.MainActivity
import com.google.android.material.snackbar.Snackbar

open class BaseFragment: Fragment() {

    fun navigateToHomeActivity() {
        activity?.let {
            val intent = Intent(it, MainActivity::class.java)
            startActivity(intent)
            it.overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
            it.finish()
        }
    }

    fun showSnackBar(view: View, message: String) {
        Snackbar.make(
            view,
            message,
            Snackbar.LENGTH_SHORT
        ).show()
    }
}