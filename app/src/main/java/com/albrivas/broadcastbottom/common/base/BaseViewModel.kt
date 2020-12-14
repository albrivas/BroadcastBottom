/*
 * File: BaseViewModel.kt
 * Project: BroadcastBottom
 *
 * Created by albrivas on 14/12/2020
 * Copyright © 2019 Alberto Rivas. All rights reserved.
 */

package com.albrivas.broadcastbottom.common.base

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

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