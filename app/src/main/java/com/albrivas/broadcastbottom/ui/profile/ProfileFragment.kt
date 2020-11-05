package com.albrivas.broadcastbottom.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.albrivas.broadcastbottom.common.base.BaseFragment
import com.albrivas.broadcastbottom.databinding.FragmentProfileBinding
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class ProfileFragment : BaseFragment() {

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
    }

    private fun instances() {
        viewModel.apply {

        }
    }

}