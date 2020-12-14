/*
 * File: ProfileFragment.kt
 * Project: BroadcastBottom
 *
 * Created by albrivas on 14/12/2020
 * Copyright © 2019 Alberto Rivas. All rights reserved.
 */

package com.albrivas.broadcastbottom.ui.profile

import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.InputType
import android.view.*
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.Observer
import com.albrivas.broadcastbottom.R
import com.albrivas.broadcastbottom.common.base.BaseFragment
import com.albrivas.broadcastbottom.common.base.BaseViewModel
import com.albrivas.broadcastbottom.common.loadUrl
import com.albrivas.broadcastbottom.databinding.FragmentProfileBinding
import com.albrivas.broadcastbottom.domain.model.User
import kotlinx.android.synthetic.main.alert_dialog_profile_information.view.*
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel
import java.util.*
import com.albrivas.broadcastbottom.ui.profile.ProfileViewModel.UiModel.*

class ProfileFragment : BaseFragment() {

    companion object {
        private const val PICK_IMAGE = 100
    }

    private val viewModel: ProfileViewModel by lifecycleScope.viewModel(this)
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        instances()
        observers()
        actions()
    }

    private fun actions() {
        binding.labelName.setOnClickListener {
            dialogEditProfile(
                binding.labelName,
                R.string.hint_name,
                InputType.TYPE_CLASS_TEXT,
                R.string.profile_name
            )
        }
        binding.labelBirth.setOnClickListener { selectDateBirthday() }
        binding.labelEmail.setOnClickListener {
            dialogEditProfile(
                binding.labelEmail,
                R.string.hint_email,
                InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS,
                R.string.profile_email
            )
        }
        binding.labelPhone.setOnClickListener {
            dialogEditProfile(
                binding.labelPhone,
                R.string.hint_phone,
                InputType.TYPE_CLASS_PHONE,
                R.string.profile_phone
            )
        }
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

    override fun updateUi(model: BaseViewModel.UiModelBase) {
        super.updateUi(model)
        when (model) {
            is UriImageProfile ->
                model.uri?.let { binding.imageProfile.loadUrl(it) }
            is ErrorUpload -> showSnackBar(
                binding.containerProfile,
                model.error
            )
            is SelectImageGallery -> openGallery()
            is UploadSuccess -> setImageProfile(model.uri)
            is DownloadSuccess -> setImageProfile(model.uri)
            is UserInformation -> setUserInformation(model.user)
            is CloseSession -> navigateToLoginActivity()
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

    private fun setUserInformation(user: User) {
        binding.user = user
    }

    private fun getUserView(): User {
        return User(
            binding.labelEmail.text.toString(),
            binding.labelBirth.text.toString(),
            binding.labelName.text.toString(),
            binding.labelPhone.text.toString()
        )
    }

    private fun dialogEditProfile(label: AppCompatTextView, hint: Int, inputType: Int, title: Int) {
        val dialogLayout = layoutInflater.inflate(
            R.layout.alert_dialog_profile_information,
            binding.containerProfile,
            false
        )
        dialogLayout.apply {
            editText.hint = getString(hint)
            editText.setText(label.text.toString())
            editText.inputType = inputType
        }

        AlertDialog.Builder(requireContext()).apply {
            setTitle(getString(title))
            setView(dialogLayout)
            setNegativeButton(getString(R.string.button_cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            setPositiveButton(getString(R.string.button_edit)) { _, _ ->
                label.text = dialogLayout.editText.text.toString()
                viewModel.updateInformationProfile(getUserView())
            }
        }.show()
    }

    private fun selectDateBirthday() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val dateSetListener =
            DatePickerDialog.OnDateSetListener { _, y, monthOfYear, dayOfMonth ->
                val realMonth = monthOfYear + 1
                val d = if (dayOfMonth < 10) "0$dayOfMonth" else "$dayOfMonth"
                val m = if (realMonth < 10) "0$realMonth" else "$realMonth"
                binding.labelBirth.text = getString(R.string.date_format, d, m, y.toString())
                viewModel.updateInformationProfile(getUserView())
            }

        val datePicker = DatePickerDialog(requireContext(), dateSetListener, year, month, day)
        datePicker.datePicker.maxDate = calendar.timeInMillis
        datePicker.show()
    }
}