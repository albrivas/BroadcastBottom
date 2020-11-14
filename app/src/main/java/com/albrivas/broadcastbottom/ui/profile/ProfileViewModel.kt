package com.albrivas.broadcastbottom.ui.profile

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.albrivas.broadcastbottom.common.base.ScopedViewModel
import com.albrivas.broadcastbottom.usescases.UploadImageProfile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val uploadImage: UploadImageProfile,
    private val databaseReference: StorageReference,
    private val mAuth: FirebaseAuth
) : ScopedViewModel() {

    private val uid = mAuth.uid

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel> get() = _model

    init {
        if (checkUserImageProfile())
            getPhotoUrlProfile()
        else
            downloadUrlImageProfile()
    }

    sealed class UiModel {
        class UriImageProfile(val uri: Uri?) : UiModel()
        class ErrorUpload(val error: String) : UiModel()
        class UploadSuccess(val uri: Uri) : UiModel()
        class DownloadSuccess(val uri: Uri) : UiModel()
        object SelectImageGallery : UiModel()
    }

    fun uploadImageProfile(uri: Uri) {
        launch {
            uploadImage.upload(uri, uid!!) { exception, uri ->
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
            uploadImage.download(uid!!) { exception, uri ->
                if (exception != null)
                    _model.value = UiModel.ErrorUpload(exception.message!!)
                else {
                    _model.value = UiModel.DownloadSuccess(uri!!)
                }
            }
        }
    }
}