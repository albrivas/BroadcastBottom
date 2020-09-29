package com.albrivas.broadcastbottom.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.albrivas.broadcastbottom.R
import com.albrivas.broadcastbottom.data.model.FieldType
import com.albrivas.broadcastbottom.data.model.ValidatorField
import com.albrivas.broadcastbottom.databinding.LoginFragmentBinding
import com.albrivas.broadcastbottom.common.Event
import com.albrivas.broadcastbottom.common.toast
import com.albrivas.broadcastbottom.ui.base.MainActivity
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by lifecycleScope.viewModel(this)
    private lateinit var binding: LoginFragmentBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        instances()
        observers()
    }

    private fun observers() {
        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))
    }

    private fun instances() {
        binding.apply {
            viewmodel = viewModel
            lifecycleOwner = this@LoginFragment
        }
        navController = findNavController()
    }

    private fun updateUi(model: LoginViewModel.UiModel) {
        when (model) {
            is LoginViewModel.UiModel.NavigateCreateAccount -> navigateToSignUp(model.event)
            is LoginViewModel.UiModel.NavigateResetPassword -> navigateToResetPassword(model.event)
            is LoginViewModel.UiModel.NavigateToHome -> navigateToHomeActivity()
            is LoginViewModel.UiModel.ErrorLogin -> context?.toast(model.exception.message!!)
            is LoginViewModel.UiModel.ErrorFields -> validateFields(model.validatorField)
        }
    }

    private fun validateFields(validatorField: ValidatorField) {
        when (validatorField.fieldType) {
            FieldType.EMAIL -> binding.inputEmail.error = getString(validatorField.errorMessage)
            FieldType.PASSWORD -> binding.inputPassword.error =
                getString(validatorField.errorMessage)
            FieldType.ACCOUNT -> { }
            FieldType.EMAIL_FORMATTED -> binding.inputEmail.error =
                getString(validatorField.errorMessage)
        }
    }

    private fun navigateToSignUp(event: Event<String>) {
        event.getContentIfNotHandled()?.let {
            val action = LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
            navController.navigate(action)
        }
    }

    private fun navigateToResetPassword(event: Event<String>) {
        event.getContentIfNotHandled()?.let {
            val action =
                LoginFragmentDirections.actionLoginFragmentToResetPasswordFragment()
            navController.navigate(action)
        }
    }

    private fun navigateToHomeActivity() {
        activity?.let {
            val intent = Intent(it, MainActivity::class.java)
            startActivity(intent)
            it.overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
            it.finish()
        }
    }
}