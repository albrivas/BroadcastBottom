package com.albrivas.broadcastbottom.common.base

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.albrivas.broadcastbottom.ui.login.LoginViewModel

abstract class BaseViewModel : ViewModel(), Scope by Scope.Impl() {

    val _model = MutableLiveData<UiModelBase>()
    val model: LiveData<UiModelBase> get() = _model

    open class UiModelBase {
        object Loading: UiModelBase()
    }

    init {
        initScope()
    }

    @CallSuper
    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }

    fun showLoading() {
        _model.value = UiModelBase.Loading
    }
}