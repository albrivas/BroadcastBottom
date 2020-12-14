/*
 * File: PreferencesKey.kt
 * Project: BroadcastBottom
 *
 * Created by albrivas on 14/12/2020
 * Copyright © 2019 Alberto Rivas. All rights reserved.
 */

package com.albrivas.broadcastbottom.data.preferencestore

import androidx.datastore.preferences.core.preferencesKey

object PreferencesKey {
    val USER_NAME = preferencesKey<String>("user_name")
    val EMAIL = preferencesKey<String>("email")
    val PHONE = preferencesKey<String>("phone")
}