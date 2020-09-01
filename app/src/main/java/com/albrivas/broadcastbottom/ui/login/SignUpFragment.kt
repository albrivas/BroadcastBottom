package com.albrivas.broadcastbottom.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.albrivas.broadcastbottom.databinding.SignUpFragmentBinding
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class SignUpFragment : Fragment() {

    private lateinit var binding: SignUpFragmentBinding
    private val viewModel: LoginViewModel by lifecycleScope.viewModel(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SignUpFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


}