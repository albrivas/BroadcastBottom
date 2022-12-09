package com.albrivas.broadcastbottom.data.source

import androidx.activity.result.IntentSenderRequest
import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.albrivas.broadcastbottom.BuildConfig
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Named

class FirebaseDataSourceImp(
    private val mAuth: FirebaseAuth,
    private val oneTapClient: SignInClient,
) : FirebaseDataSource {

    private val signInRequest by lazy {
        BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(BuildConfig.WEB_CLIENT_ID)
                    .setFilterByAuthorizedAccounts(true)
                    .build()
            )
            .build()
    }

    private val signUpRequest: BeginSignInRequest by lazy {
        BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(BuildConfig.WEB_CLIENT_ID)
                    .setFilterByAuthorizedAccounts(false)
                    .build()
            )
            .build()
    }

    override fun loginWithEmailAndPassword(
        email: String,
        password: String,
        result: (Boolean, Exception?) -> Unit
    ) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful)
                result(true, null)
            else
                result(false, task.exception)
        }
    }

    override fun createAccount(
        email: String,
        password: String,
        result: (Boolean, Exception?) -> Unit
    ) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful)
                    result(true, null)
                else
                    result(false, task.exception)
            }
    }

    override fun forgotPassword(
        email: String,
        result: (Boolean, Exception?) -> Unit
    ) {
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
            if (task.isSuccessful)
                result(true, null)
            else
                result(false, task.exception)
        }
    }

    override fun loginFacebook(credential: AuthCredential, result: (Boolean, Exception?) -> Unit) {
        mAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful)
                result(true, null)
            else
                result(false, task.exception)
        }
    }

    override suspend fun loginFirebase(credential: AuthCredential): Either<Exception, Unit> {
        return try {
            val result = mAuth.signInWithCredential(credential).await()
            if (result.user != null)
                Unit.right()
            else
                Exception().left()
        } catch (ex: Exception) {
            ex.left()
        }
    }

    override suspend fun oneTapSignInWithGoogle(): Either<Exception, IntentSenderRequest> {
        return try {
            val result = oneTapClient.beginSignIn(signInRequest).await()
            val intentSenderRequest =
                IntentSenderRequest.Builder(result.pendingIntent.intentSender).build()
            intentSenderRequest.right()
        } catch (ex: Exception) {
            ex.left()
        }
    }

    override suspend fun signUpWithGoogle(): Either<Exception, IntentSenderRequest> {
        return try {
            val result = oneTapClient.beginSignIn(signUpRequest).await()
            val intentSenderRequest =
                IntentSenderRequest.Builder(result.pendingIntent.intentSender).build()
            intentSenderRequest.right()
        } catch (ex: Exception) {
            ex.left()
        }
    }
}