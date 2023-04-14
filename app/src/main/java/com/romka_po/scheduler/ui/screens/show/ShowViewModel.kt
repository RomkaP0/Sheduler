package com.romka_po.scheduler.ui.screens.show

import androidx.lifecycle.ViewModel
import com.romka_po.scheduler.model.AppNavigator
import com.romka_po.scheduler.model.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShowViewModel @Inject constructor(private val appNavigator: AppNavigator):ViewModel(){
    fun onNavigateToUsersButtonClicked() {
        appNavigator.tryNavigateTo(Destination.AddEvent("26.10.2002"))
    }


}