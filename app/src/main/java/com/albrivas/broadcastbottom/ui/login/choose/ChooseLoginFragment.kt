/*
 * File: ChooseLoginFragment.kt
 * Project: BroadcastBottom
 *
 * Created by albrivas on 14/12/2020
 * Copyright © 2019 Alberto Rivas. All rights reserved.
 */

package com.albrivas.broadcastbottom.ui.login.choose

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.albrivas.broadcastbottom.BuildConfig
import com.albrivas.broadcastbottom.common.Event
import com.albrivas.broadcastbottom.common.base.BaseFragment
import com.albrivas.broadcastbottom.common.base.BaseViewModel
import com.albrivas.broadcastbottom.databinding.FragmentChooseLoginBinding
import com.albrivas.broadcastbottom.ui.login.viewmodel.LoginViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class ChooseLoginFragment : BaseFragment() {

    companion object {
        private const val RC_SIGN_IN = 9001
    }

    private val viewModel: LoginViewModel by lifecycleScope.viewModel(this)
    private lateinit var binding: FragmentChooseLoginBinding
    private lateinit var navController: NavController
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChooseLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        instances()
        observers()
        actions()
    }

    private fun actions() {
        binding.buttonLoginGoogle.setOnClickListener { signInGoogle() }
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

        instancesGoogle()
    }

    private fun instancesGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(BuildConfig.GOOGLE_API_KEY)
            .requestEmail()
            .build()

        activity?.let {
            googleSignInClient = GoogleSignIn.getClient(it, gso)
        }
    }

    override fun updateUi(model: BaseViewModel.UiModelBase) {
        super.updateUi(model)
        when (model) {
            is LoginViewModel.UiModel.NavigateCreateAccount -> navigateToSignUp(model.event)
            is LoginViewModel.UiModel.NavigateSignIn -> navigateToSignIn(model.event)
            is LoginViewModel.UiModel.NavigateToHome -> navigateToHomeActivity()
        }
    }

    private fun signInGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                viewModel.signInWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Snackbar.make(binding.chooseContainer, e.message!!, Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}