package com.albrivas.broadcastbottom.ui.login.choose

import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.albrivas.broadcastbottom.R
import com.albrivas.broadcastbottom.common.base.BaseFragment
import com.albrivas.broadcastbottom.common.composeView
import com.albrivas.uikit.button.ButtonChooseLogin
import com.albrivas.uikit.button.LoginTypeModel
import com.albrivas.uikit.text.LabelLinkText
import com.albrivas.uikit.text.TitleText
import com.google.android.gms.auth.api.identity.SignInClient
import org.koin.android.ext.android.inject
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel


class ChooseLoginScreen : BaseFragment() {

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = composeView { ChooseLoginView() }


    @Composable
    private fun ChooseLoginView(viewModel: ChooseLoginViewModel = getViewModel()) {
        val oneTapClient by inject<SignInClient>()

        val appState = rememberChooseLoginState(navController = findNavController())
        val resultLauncherGoogle =
            rememberLauncherForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val credential = oneTapClient.getSignInCredentialFromIntent(result.data)
                    credential.googleIdToken?.let { token ->
                        viewModel.login(token)
                    }
                }
            }

        with(viewModel) {
            state.loginError?.let {
                viewModel.initUiState(state = this.state.copy(loginError = null))
            }

            state.intentSenderRequest?.let {
                resultLauncherGoogle.launch(it)
            }
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.White)
                .padding(
                    horizontal = 16.dp,
                    vertical = 16.dp
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TitleText(text = R.string.title_login)
            Spacer(Modifier.size(size = 34.dp))
            ButtonChooseLogin(
                type = LoginTypeModel.USER_PASS,
                textButton = R.string.choose_user_email
            ) {
                appState.navigateToSignIn()
            }
            Spacer(Modifier.size(size = 16.dp))
            ButtonChooseLogin(
                type = LoginTypeModel.GOOGLE,
                textButton = R.string.choose_google
            ) {
                viewModel.prepareGoogleLogin()
            }
            Spacer(Modifier.size(size = 16.dp))
            ButtonChooseLogin(
                type = LoginTypeModel.FACEBOOK,
                textButton = R.string.choose_facebook
            ) {
                Toast.makeText(it, "Not available for the moment", Toast.LENGTH_SHORT).show()
            }
            Spacer(Modifier.size(size = 36.dp))
            LabelLinkText(text = R.string.label_login) {
                appState.navigateToSignUp()
            }
        }
    }
}

