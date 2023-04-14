package com.romka_po.scheduler

import androidx.lifecycle.ViewModel
import com.romka_po.scheduler.model.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    appNavigator: AppNavigator
) : ViewModel() {

    val navigationChannel = appNavigator.navigationChannel
}