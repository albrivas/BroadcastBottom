package com.albrivas.broadcastbottom.ui.login.choose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberChooseLoginState(
    navController: NavController,
    coroutineScope: CoroutineScope = rememberCoroutineScope()
): ChooseLoginState = remember { ChooseLoginState(navController, coroutineScope) }

class ChooseLoginState(
    private val navController: NavController,
    val coroutineScope: CoroutineScope
) {
    fun navigateToSignUp() {
        val action = ChooseLoginScreenDirections.actionChooseLoginFragmentToSignUpFragment()
        navController.navigate(action)
    }

    fun navigateToSignIn() {
        val action = ChooseLoginScreenDirections.actionChooseLoginFragmentToLoginFragment()
        navController.navigate(action)
    }
}