package com.albrivas.broadcastbottom.ui.profile

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.albrivas.broadcastbottom.common.base.ScopedViewModel
import com.albrivas.broadcastbottom.domain.model.User
import com.albrivas.broadcastbottom.domain.model.toHasMap
import com.albrivas.broadcastbottom.usescases.UserDataUC
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.launch
import java.util.*

class ProfileViewModel(
    private val userData: UserDataUC,
    private val databaseReference: StorageReference,
    private val mAuth: FirebaseAuth
) : ScopedViewModel() {

    private val uid = mAuth.uid

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel> get() = _model

    init {
        getInformationProfile()
    }

    sealed class UiModel {
        class UriImageProfile(val uri: Uri?) : UiModel()
        class ErrorUpload(val error: String) : UiModel()
        class UploadSuccess(val uri: Uri) : UiModel()
        class DownloadSuccess(val uri: Uri) : UiModel()
        object SelectImageGallery : UiModel()
        class UserInformation(val user: User) : UiModel()
    }

    fun uploadImageProfile(uri: Uri) {
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

    fun selectImage() {
        _model.value = UiModel.SelectImageGallery
    }

    private fun getPhotoUrlProfile() {
        val photo = mAuth.currentUser?.photoUrl
        _model.value = UiModel.UriImageProfile(photo)
    }

    private fun checkUserImageProfile(): Boolean {
        var isCheck = false
        val uid = mAuth.currentUser?.uid
        databaseReference.child("/users/$uid").listAll().addOnSuccessListener {
            isCheck = it.items.size <= 0
        }

        return isCheck
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

    private fun getInformationProfile() {
        val currentUser = mAuth.currentUser
        val user =
            User(currentUser?.displayName, Date(), currentUser?.phoneNumber, currentUser?.email)
        _model.value = UiModel.UserInformation(user)
        
        updateInformationProfile(user)

        if (checkUserImageProfile())
            getPhotoUrlProfile()
        else
            downloadUrlImageProfile()

    }

    private fun updateInformationProfile(user: User) {
        launch {
            userData.updateInformation(uid!!, user.toHasMap()) {

            }
        }
    }

}