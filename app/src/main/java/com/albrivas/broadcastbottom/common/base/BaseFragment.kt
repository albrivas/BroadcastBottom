package com.albrivas.broadcastbottom.common.base

import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import com.albrivas.broadcastbottom.R
import com.albrivas.broadcastbottom.common.view.LoadingDialog
import com.albrivas.broadcastbottom.ui.base.MainActivity
import com.albrivas.broadcastbottom.ui.login.LoginBaseActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

open class BaseFragment() : Fragment() {

    private var progressBar: LoadingDialog? = null

    fun navigateToHomeActivity() {
        activity?.let {
            val intent = Intent(it, MainActivity::class.java)
            startActivity(intent)
            it.overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
            it.finish()
        }
    }

    fun navigateToLoginActivity() {
        activity?.let {
            val intent = Intent(it, LoginBaseActivity::class.java)
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

    private fun showLoading() {
        progressBar = LoadingDialog(requireContext())
        progressBar?.apply {
            dismiss()
            setCanceledOnTouchOutside(false)
            setCancelable(false)
            if (!isShowing)
                show()
        }
    }

    private fun hideLoading() {
        progressBar?.dismissWithAnimation()
    }

    open fun updateUi(model: BaseViewModel.UiModelBase) {
        if (model is BaseViewModel.UiModelBase.Loading) showLoading() else hideLoading()
    }


}