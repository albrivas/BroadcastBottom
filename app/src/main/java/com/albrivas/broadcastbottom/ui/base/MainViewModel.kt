package com.albrivas.broadcastbottom.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel> get() = _model

    sealed class UiModel {
        object DeleteNotifications : UiModel()
    }

    fun onDeleteNotifications() {
        _model.value = UiModel.DeleteNotifications
    }
}