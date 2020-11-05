package com.albrivas.broadcastbottom.usescases

import android.net.Uri
import com.albrivas.broadcastbottom.common.Either
import com.albrivas.broadcastbottom.data.repository.UploadImageRepository

class UploadImageProfile(
    private val repository: UploadImageRepository
) {
    suspend fun invoke(uri: Uri): Either<Exception, Boolean> = repository.uploadImage(uri)
}