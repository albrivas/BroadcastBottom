package com.albrivas.broadcastbottom.data.repository

import android.net.Uri
import com.albrivas.broadcastbottom.common.Either
import java.lang.Exception

class UploadImageRepository {

    suspend fun uploadImage(uri: Uri): Either<Exception, Boolean> {


        return Either.Right(true)
    }
}