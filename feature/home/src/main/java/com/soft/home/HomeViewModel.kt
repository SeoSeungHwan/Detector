package com.soft.home

import com.soft.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class HomeViewModel: BaseViewModel(){

    private val TAG = "HomeViewModel"

    private val _navigate: MutableSharedFlow<HomeAction> = MutableSharedFlow<HomeAction>()
    val navigate: SharedFlow<HomeAction> = _navigate.asSharedFlow()

}