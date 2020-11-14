package com.albrivas.broadcastbottom.usescases

import android.net.Uri
import com.albrivas.broadcastbottom.data.repository.UploadImageRepository
import java.lang.Exception

class UploadImageProfile(
    private val repository: UploadImageRepository
) {
    fun upload(uri: Uri, uid: String, result: (Exception?, Uri?) -> Unit) =
        repository.uploadImage(uri, uid, result)

    fun download(uid: String, result: (Exception?, Uri?) -> Unit) =
        repository.downloadImage(uid, result)

}