package com.albrivas.broadcastbottom.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.albrivas.broadcastbottom.domain.model.FieldType
import com.albrivas.broadcastbottom.domain.model.ValidatorField
import com.albrivas.broadcastbottom.databinding.FragmentResetPasswordBinding
import com.albrivas.broadcastbottom.common.Event
import com.albrivas.broadcastbottom.common.toast
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class ResetPasswordFragment : Fragment() {

    private lateinit var binding: FragmentResetPasswordBinding
    private val viewModel: LoginViewModel by lifecycleScope.viewModel(this)
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResetPasswordBinding.inflate(inflater, container, false)
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
            lifecycleOwner = this@ResetPasswordFragment
        }
        navController = findNavController()
    }

    private fun observers() {
        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))
    }

    private fun updateUi(model: LoginViewModel.UiModel) {
        when (model) {
            is LoginViewModel.UiModel.NavigateSignIn -> navigateToSignIn(model.event)
            is LoginViewModel.UiModel.ErrorLogin -> context?.toast(model.exception.message!!)
            is LoginViewModel.UiModel.ErrorFields -> validateFields(model.validatorField)
        }
    }

    private fun validateFields(validatorField: ValidatorField) {
        when (validatorField.fieldType) {
            FieldType.EMAIL -> binding.inputEmailReset.error = getString(validatorField.errorMessage)
            FieldType.EMAIL_FORMATTED -> binding.inputEmailReset.error =
                getString(validatorField.errorMessage)
            else -> {}
        }
    }

    private fun navigateToSignIn(event: Event<String>) {
        event.getContentIfNotHandled()?.let {
            val action =
                ResetPasswordFragmentDirections.actionResetPasswordFragmentToLoginFragment()
            navController.navigate(action)
        }
    }

}