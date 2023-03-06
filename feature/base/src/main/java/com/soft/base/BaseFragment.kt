package com.soft.base

import android.app.Application
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.DialogFragmentNavigator
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

abstract class BaseFragment<T : ViewDataBinding, R : BaseViewModel>(layoutId: Int) : Fragment(layoutId) {

    private var _binding: T? = null
    val binding get()= requireNotNull(_binding)

    abstract val layoutResourceId: Int

    abstract val viewModel: R

    abstract fun initStartView()

    abstract fun initDataBinding()

    abstract fun initAfterBinding()

    private var isSetBackButtonValid = false

    /**
     * Exception을 처리할 SharedFlow
    */
    protected var exception: SharedFlow<Throwable>? = null
    private var toast: Toast? = null

    init {
        lifecycleScope.launchWhenStarted {
            launch {
                exception?.collect { exception ->
                    showToastMessage(exception)
                }
            }

            launch {
                viewModel.errorEvent.collect { e ->
                    showToastMessage(e)
                    Log.e("Detector", "onStart: ${e}")
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutResourceId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        initStartView()
        initDataBinding()
        initAfterBinding()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        toast?.cancel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Toast Message 관련 함수
    protected fun showToastMessage(e: Throwable?) {
        toast?.cancel()
        toast = Toast.makeText(activity, e?.message, Toast.LENGTH_SHORT)?.apply { show() }
    }

    // Toast Message 관련 함수
    protected fun toastMessage(message: String) {
        toast?.cancel()
        toast = Toast.makeText(activity, message, Toast.LENGTH_SHORT)?.apply { show() }
    }

    // navigation 중복체크 관리 <- checkNavigation 대신 사용할것
    protected fun Fragment.navigate(directions: NavDirections) {
        val controller = findNavController()
        val currentDestination = (controller.currentDestination as? FragmentNavigator.Destination)?.className
            ?: (controller.currentDestination as? DialogFragmentNavigator.Destination)?.className
        if (currentDestination == this.javaClass.name) {
            controller.navigate(directions)
        }
    }
}

