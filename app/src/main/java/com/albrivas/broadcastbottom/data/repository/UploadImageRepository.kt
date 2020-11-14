package com.albrivas.broadcastbottom.data.repository

import android.net.Uri
import com.google.firebase.storage.StorageReference

class UploadImageRepository(
    private val databaseReference: StorageReference
) {

    fun uploadImage(uri: Uri, uid: String, result: (Exception?, Uri?) -> Unit) {
        val imageProfileRef = databaseReference.child("users/$uid/profile")

        imageProfileRef.putFile(uri).addOnSuccessListener {
            it.metadata?.reference?.downloadUrl?.addOnSuccessListener { uri ->
                result(null, uri)
            }
        }.addOnFailureListener {
            result(it, null)
        }
    }

    fun downloadImage(uid: String, result: (Exception?, Uri?) -> Unit) {
        val imageProfileRef = databaseReference.child("users/$uid/profile")

        imageProfileRef.downloadUrl.addOnSuccessListener { uri ->
            result(null, uri)
        }.addOnFailureListener {
            result(it, null)
        }

    }
}