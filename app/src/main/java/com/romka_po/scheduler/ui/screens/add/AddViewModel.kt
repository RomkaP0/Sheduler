package com.romka_po.scheduler.ui.screens.add

import androidx.lifecycle.ViewModel
import com.romka_po.scheduler.model.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(private val appNavigator: AppNavigator) : ViewModel() {

    //    fun insertEvent(event:Event) = viewModelScope.launch {
//        repository.insertEvent(event)
//    }
    fun onNavigateToUsersButtonClicked() {
        appNavigator.tryNavigateBack()
    }


}