/*
 * File: SignUpFragment.kt
 * Project: BroadcastBottom
 *
 * Created by albrivas on 14/12/2020
 * Copyright © 2019 Alberto Rivas. All rights reserved.
 */

package com.albrivas.broadcastbottom.ui.login.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.albrivas.broadcastbottom.domain.model.FieldType
import com.albrivas.broadcastbottom.domain.model.ValidatorField
import com.albrivas.broadcastbottom.common.Event
import com.albrivas.broadcastbottom.common.base.BaseFragment
import com.albrivas.broadcastbottom.common.base.BaseViewModel
import com.albrivas.broadcastbottom.common.toast
import com.albrivas.broadcastbottom.databinding.FragmentSignUpBinding
import com.albrivas.broadcastbottom.ui.login.viewmodel.LoginViewModel
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class SignUpFragment : BaseFragment() {

    private lateinit var binding: FragmentSignUpBinding
    private val viewModel: LoginViewModel by lifecycleScope.viewModel(this)
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        instances()
        observers()
    }

    private fun instances() {
        binding.apply {
            viewmodel = viewModel
            lifecycleOwner = this@SignUpFragment
        }
        navController = findNavController()
    }

    private fun observers() {
        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))
    }


    override fun updateUi(model: BaseViewModel.UiModelBase) {
        super.updateUi(model)
        when (model) {
            is LoginViewModel.UiModel.NavigateCreateAccount -> navigateToSignIn(model.event)
            is LoginViewModel.UiModel.ErrorLogin -> context?.toast(model.exception.message!!)
            is LoginViewModel.UiModel.NavigateSignIn -> navigateToSignIn(model.event)
            is LoginViewModel.UiModel.ErrorFields -> validateFields(model.validatorField)
        }
    }

    private fun validateFields(validatorField: ValidatorField) {
        when (validatorField.fieldType) {
            FieldType.EMAIL -> binding.inputEmailReg.error = getString(validatorField.errorMessage)
            FieldType.PASSWORD -> binding.inputPassReg.error =
                getString(validatorField.errorMessage)
            FieldType.ACCOUNT -> binding.inputNameReg.error =
                getString(validatorField.errorMessage)
            FieldType.EMAIL_FORMATTED -> binding.inputEmailReg.error =
                getString(validatorField.errorMessage)
        }
    }

    private fun navigateToSignIn(event: Event<String>) {
        event.getContentIfNotHandled()?.let {
            val action = SignUpFragmentDirections.actionSignUpFragmentToLoginFragment()
            navController.navigate(action)
        }
    }
}