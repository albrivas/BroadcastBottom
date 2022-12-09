package com.albrivas.broadcastbottom.data.repository.login

import androidx.activity.result.IntentSenderRequest
import arrow.core.Either
import com.albrivas.broadcastbottom.data.source.FirebaseDataSource
import com.google.firebase.auth.AuthCredential

class LoginGoogleRepository(
    private val firebaseDataSource: FirebaseDataSource
) {
    suspend fun getIntentLoginGoogle(): Either<Exception, IntentSenderRequest> {
        // TODO: save the user information (datastore) or create datasource to
        //  get information through Google
        val result = firebaseDataSource.oneTapSignInWithGoogle()

        return if (result.isRight())
            result
        else
            firebaseDataSource.signUpWithGoogle()
    }

    suspend fun loginFirebase(authCredential: AuthCredential): Either<Exception, Unit> =
        firebaseDataSource.loginFirebase(credential = authCredential)

}