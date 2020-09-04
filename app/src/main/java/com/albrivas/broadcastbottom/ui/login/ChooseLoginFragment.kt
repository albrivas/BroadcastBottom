package com.albrivas.broadcastbottom.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.albrivas.broadcastbottom.R
import com.albrivas.broadcastbottom.databinding.FragmentChooseLoginBinding
import com.albrivas.broadcastbottom.ui.common.Event
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class ChooseLoginFragment : Fragment() {

    private val viewModel: LoginViewModel by lifecycleScope.viewModel(this)
    private lateinit var binding: FragmentChooseLoginBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChooseLoginBinding.inflate(inflater, container, false)
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
            lifecycleOwner = this@ChooseLoginFragment
        }
        navController = findNavController()
    }

    private fun updateUi(model: LoginViewModel.UiModel) {
        when (model) {
            is LoginViewModel.UiModel.NavigateCreateAccount -> navigateToSignUp(model.event)
            is LoginViewModel.UiModel.NavigateSignIn -> navigateToSignIn(model.event)
        }
    }

    private fun navigateToSignUp(event: Event<String>) {
        event.getContentIfNotHandled()?.let {
            val action = ChooseLoginFragmentDirections.actionChooseLoginFragmentToSignUpFragment()
            navController.navigate(action)
        }
    }

    private fun navigateToSignIn(event: Event<String>) {
        event.getContentIfNotHandled()?.let {
            val action = ChooseLoginFragmentDirections.actionChooseLoginFragmentToLoginFragment()
            navController.navigate(action)
        }
    }

}