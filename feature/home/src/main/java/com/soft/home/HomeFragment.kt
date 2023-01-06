package com.soft.home

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.soft.base.BaseFragment
import com.soft.home.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {

    override val layoutResourceId: Int
        get() = R.layout.fragment_home

    override val viewModel: HomeViewModel by viewModels()

    override fun initStartView() {
        binding.apply {
            this.vm = viewModel
            this.lifecycleOwner = viewLifecycleOwner
        }
        exception = viewModel.errorEvent
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
        binding.faceMeshBtn.setOnClickListener {
            findNavController().navigate(com.soft.base.R.id.action_homeFragment_to_faceMeshFragment)
        }
    }
}


