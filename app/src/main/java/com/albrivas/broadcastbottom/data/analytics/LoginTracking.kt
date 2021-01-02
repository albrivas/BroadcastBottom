/*
 * File: LoginTracking.kt
 * Project: BroadcastBottom
 *
 * Created by albrivas on 17/12/2020
 * Copyright © 2019 Alberto Rivas. All rights reserved.
 */

package com.albrivas.broadcastbottom.data.analytics

import android.os.Bundle
import com.albrivas.broadcastbottom.domain.constants.AnalyticsConstants
import com.google.firebase.analytics.FirebaseAnalytics

class LoginTracking(
    private val analytics: FirebaseAnalytics
) {
    val bundle: Bundle by lazy { Bundle() }

    fun onSignInEmailPassword() {
        bundle.putString(FirebaseAnalytics.Param.METHOD, AnalyticsConstants.Login.SIGNIN_EMAIL_PASSWORD)
        analytics.logEvent(FirebaseAnalytics.Event.LOGIN, bundle)
    }

    fun onSignGoogle() {
        bundle.putString(FirebaseAnalytics.Param.METHOD, AnalyticsConstants.Login.SIGNIN_GOOGLE)
        analytics.logEvent(FirebaseAnalytics.Event.LOGIN, bundle)
    }

    fun onSignInFacebook() {
        bundle.putString(FirebaseAnalytics.Param.METHOD, AnalyticsConstants.Login.SIGNIN_FACEBOOK)
        analytics.logEvent(FirebaseAnalytics.Event.LOGIN, bundle)
    }

    fun onCreateAccount() {
        bundle.putString(FirebaseAnalytics.Param.METHOD, AnalyticsConstants.Login.CREATE_ACCOUNT)
        analytics.logEvent(FirebaseAnalytics.Event.LOGIN, bundle)
    }

    fun onForgotPassword() {
        bundle.putString(FirebaseAnalytics.Param.METHOD, AnalyticsConstants.Login.FORGOT_PASSWORD)
        analytics.logEvent(FirebaseAnalytics.Event.LOGIN, bundle)
    }

}