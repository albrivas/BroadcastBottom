/*
 * File: ProfileViewModel.kt
 * Project: BroadcastBottom
 *
 * Created by albrivas on 14/12/2020
 * Copyright © 2019 Alberto Rivas. All rights reserved.
 */

package com.albrivas.broadcastbottom.ui.profile

import android.net.Uri
import com.albrivas.broadcastbottom.common.base.BaseViewModel
import com.albrivas.broadcastbottom.domain.model.User
import com.albrivas.broadcastbottom.domain.model.toHasMap
import com.albrivas.broadcastbottom.domain.model.toUser
import com.albrivas.broadcastbottom.usescases.UserDataUsesCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val userData: UserDataUsesCase,
    private val databaseReference: StorageReference,
    private val fireStoreDatabase: FirebaseFirestore,
    private val mAuth: FirebaseAuth
) : BaseViewModel() {

    private val uid = mAuth.uid

    init {
        checkUserImageProfile()
        checkUserInformationFireStore()
    }

    sealed class UiModel: UiModelBase() {
        class UriImageProfile(val uri: Uri?) : UiModel()
        class ErrorUpload(val error: String) : UiModel()
        class UploadSuccess(val uri: Uri) : UiModel()
        class DownloadSuccess(val uri: Uri) : UiModel()
        object SelectImageGallery : UiModel()
        class UserInformation(val user: User) : UiModel()
        object CloseSession : UiModel()
    }

    fun selectImage() {
        _model.value = UiModel.SelectImageGallery
    }

    fun closeSession() {
        mAuth.signOut()
        _model.value = UiModel.CloseSession
    }

    //region CALLs FIREBASE

    fun uploadImageProfile(uri: Uri) {
        showLoading()
        launch {
            userData.upload(uri, uid!!) { exception, uri ->
                if (exception != null)
                    _model.value = UiModel.ErrorUpload(exception.message!!)
                else {
                    _model.value = UiModel.UploadSuccess(uri!!)
                }
            }
        }
    }

    fun updateInformationProfile(user: User) {
        launch {
            userData.updateInformation(uid!!, user.toHasMap()) {

            }
        }
    }

    private fun downloadUrlImageProfile() {
        launch {
            userData.download(uid!!) { exception, uri ->
                if (exception != null)
                    _model.value = UiModel.ErrorUpload(exception.message!!)
                else {
                    _model.value = UiModel.DownloadSuccess(uri!!)
                }
            }
        }
    }

    //endregion

    //region GET INFORMATION PROFILE (IMAGE, DATA)

    private fun getPhotoUrlProfile() {
        val photo = mAuth.currentUser?.photoUrl
        _model.value = UiModel.UriImageProfile(photo)
    }

    private fun getUserInformationFireStore() {
        fireStoreDatabase.collection("users").document(uid!!).get()
            .addOnSuccessListener { document ->
                val userMap = document.data
                userMap?.let { _model.value = UiModel.UserInformation(it.toUser()) }
            }
    }

    //endregion

    //region CHECK INFORMATION PROFILE (IMAGE, DATA)

    private fun checkUserImageProfile() {
        val uid = mAuth.currentUser?.uid
        databaseReference.child("/users/$uid").listAll().addOnSuccessListener {
            if (it.items.size <= 0)
                getPhotoUrlProfile()
            else
                downloadUrlImageProfile()
        }
    }

    private fun checkUserInformationFireStore() {
        val uid = mAuth.currentUser?.uid

        fireStoreDatabase.collection("users").document(uid!!).get().addOnSuccessListener {
            if (it.exists())
                getUserInformationFireStore()
            else
                updateUserWithInformationAuthFirebase()
        }
    }

    //endregion

    private fun updateUserWithInformationAuthFirebase() {
        val currentUser = mAuth.currentUser

        val user =
            User(
                currentUser?.email,
                null,
                currentUser?.displayName,
                currentUser?.phoneNumber
            )
        _model.value = UiModel.UserInformation(user)

        updateInformationProfile(user)
    }

}