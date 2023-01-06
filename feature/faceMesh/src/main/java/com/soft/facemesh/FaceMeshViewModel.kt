package com.soft.facemesh

import com.soft.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class FaceMeshViewModel @Inject constructor() : BaseViewModel(){

    private val TAG = "HomeViewModel"

    private val _navigate: MutableSharedFlow<FaceMeshAction> = MutableSharedFlow<FaceMeshAction>()
    val navigate: SharedFlow<FaceMeshAction> = _navigate.asSharedFlow()

}