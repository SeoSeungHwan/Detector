package com.soft.base

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

abstract class BaseActivity<T : ViewDataBinding, R : BaseViewModel> : AppCompatActivity() {

    private var _binding: T? = null
    val binding get()= requireNotNull(_binding)

    abstract val layoutResourceId: Int

    abstract val viewModel: R

    abstract fun initStartView()

    abstract fun initDataBinding()

    abstract fun initAfterBinding()

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, layoutResourceId)
        binding.lifecycleOwner = this

        initStartView()
        initDataBinding()
        initAfterBinding()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        toast?.cancel()
    }

    //Toast Message 관련 함수
    protected fun showToastMessage(e: Throwable?) {
        toast?.cancel()
        toast = Toast.makeText(this, e?.message, Toast.LENGTH_SHORT)?.apply { show() }
    }

    // Toast Message 관련 함수
    protected fun toastMessage(message: String) {
        toast?.cancel()
        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)?.apply { show() }
    }
}