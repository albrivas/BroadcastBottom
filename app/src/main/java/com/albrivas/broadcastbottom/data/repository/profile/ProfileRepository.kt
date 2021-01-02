/*
 * File: UserDataRepository.kt
 * Project: BroadcastBottom
 *
 * Created by albrivas on 14/12/2020
 * Copyright © 2019 Alberto Rivas. All rights reserved.
 */

package com.albrivas.broadcastbottom.data.repository.profile

import android.net.Uri
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference

class ProfileRepository(
    private val databaseReference: StorageReference,
    private val fireStoreDatabase: FirebaseFirestore,
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

    fun updateUserInformation(uid: String, user: HashMap<String, out Any?>, result: (Exception) -> Unit) {
        fireStoreDatabase.collection("users").document(uid).set(user)
            .addOnFailureListener {
                result(it)
            }
    }
}