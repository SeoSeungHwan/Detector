package com.soft.home

import com.soft.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class HomeViewModel: BaseViewModel() , HomeActionHandler{

    private val TAG = "HomeViewModel"

    private val _navigationEvent: MutableSharedFlow<HomeNavigationAction> = MutableSharedFlow<HomeNavigationAction>()
    val navigationEvent: SharedFlow<HomeNavigationAction> = _navigationEvent.asSharedFlow()

    override fun onFaceMashClicked() {
        baseViewModelScope.launch {
            _navigationEvent.emit(HomeNavigationAction.NavigateToFaceMesh)
        }
    }

}