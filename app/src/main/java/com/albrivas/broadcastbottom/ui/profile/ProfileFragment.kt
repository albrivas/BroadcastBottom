package com.albrivas.broadcastbottom.ui.profile

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.albrivas.broadcastbottom.common.base.BaseFragment
import com.albrivas.broadcastbottom.common.loadUrl
import com.albrivas.broadcastbottom.databinding.FragmentProfileBinding
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class ProfileFragment : BaseFragment() {

    companion object {
        private const val PICK_IMAGE = 100
    }

    private val viewModel: ProfileViewModel by lifecycleScope.viewModel(this)
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        instances()
        observers()
    }

    private fun observers() {
        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))
    }

    private fun instances() {
        binding.apply {
            viewmodel = viewModel
            lifecycleOwner = this@ProfileFragment
        }
    }

    private fun updateUi(model: ProfileViewModel.UiModel) {
        when (model) {
            is ProfileViewModel.UiModel.UriImageProfile -> {
                model.uri?.let { binding.imageProfile.loadUrl(it) }
            }
            is ProfileViewModel.UiModel.ErrorUpload -> showSnackBar(
                binding.containerProfile,
                model.error
            )
            is ProfileViewModel.UiModel.SelectImageGallery -> openGallery()
            is ProfileViewModel.UiModel.UploadSuccess -> setImageProfile(model.uri)
            is ProfileViewModel.UiModel.DownloadSuccess -> setImageProfile(model.uri)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            val imageUri = data?.data
            imageUri?.let { viewModel.uploadImageProfile(it) }
        }
    }

    private fun openGallery() {
        val gallery = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.INTERNAL_CONTENT_URI
        )
        startActivityForResult(gallery, PICK_IMAGE)
    }

    private fun setImageProfile(uri: Uri) {
        binding.imageProfile.loadUrl(uri)
    }

}