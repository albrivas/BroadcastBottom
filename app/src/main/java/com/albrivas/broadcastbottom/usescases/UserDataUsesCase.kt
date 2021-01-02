/*
 * File: UserDataUsesCase.kt
 * Project: BroadcastBottom
 *
 * Created by albrivas on 14/12/2020
 * Copyright © 2019 Alberto Rivas. All rights reserved.
 */

package com.albrivas.broadcastbottom.usescases

import android.net.Uri
import com.albrivas.broadcastbottom.data.repository.profile.ProfileRepository
import java.lang.Exception

class UserDataUsesCase(
    private val repository: ProfileRepository
) {
    fun upload(uri: Uri, uid: String, result: (Exception?, Uri?) -> Unit) =
        repository.uploadImage(uri, uid, result)

    fun download(uid: String, result: (Exception?, Uri?) -> Unit) =
        repository.downloadImage(uid, result)

    fun updateInformation(uid: String, user: HashMap<String, out Any?>, result: (Exception) -> Unit) =
        repository.updateUserInformation(uid, user, result)

}