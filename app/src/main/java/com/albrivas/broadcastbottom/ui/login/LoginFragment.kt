package com.albrivas.broadcastbottom.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.albrivas.broadcastbottom.databinding.LoginFragmentBinding
import com.albrivas.broadcastbottom.ui.common.Event
import com.albrivas.broadcastbottom.ui.common.toast
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
            is LoginViewModel.UiModel.ErrorLogin -> context?.toast(model.exception.message!!)
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
}