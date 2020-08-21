package com.albrivas.broadcastbottom.ui.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId

class MainViewModel : ViewModel() {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel> get() = _model

    init {
        getTokenFirebase()
    }

    sealed class UiModel {
        object DeleteNotifications : UiModel()
    }

    fun onDeleteNotifications() {
        _model.value = UiModel.DeleteNotifications
    }

    private fun getTokenFirebase() {
        FirebaseInstanceId.getInstance()
            .instanceId.addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful)
                    return@OnCompleteListener

                Log.d("Device_Token", task.result?.token)
            })
    }
}