/*
 * File: PreferenceStorage.kt
 * Project: BroadcastBottom
 *
 * Created by albrivas on 14/12/2020
 * Copyright © 2019 Alberto Rivas. All rights reserved.
 */

package com.albrivas.broadcastbottom.data.preferencestore

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.createDataStore

class PreferenceStorage(context: Context) {

    val dataStore = context.createDataStore(name = "user_preference")

    suspend inline fun <reified T> storeValue(key: Preferences.Key<T>, value: Any) {
        dataStore.edit {
            it[key] = value as T
        }
    }
}