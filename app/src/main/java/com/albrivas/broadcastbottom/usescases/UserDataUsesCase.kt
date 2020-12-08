package com.albrivas.broadcastbottom.usescases

import android.net.Uri
import com.albrivas.broadcastbottom.data.repository.UserDataRepository
import java.lang.Exception

class UserDataUsesCase(
    private val repository: UserDataRepository
) {
    fun upload(uri: Uri, uid: String, result: (Exception?, Uri?) -> Unit) =
        repository.uploadImage(uri, uid, result)

    fun download(uid: String, result: (Exception?, Uri?) -> Unit) =
        repository.downloadImage(uid, result)

    fun updateInformation(uid: String, user: HashMap<String, out Any?>, result: (Exception) -> Unit) =
        repository.updateUserInformation(uid, user, result)

}