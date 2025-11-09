package com.thulanicreatives.stackoverflow.presentation.network_connectivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.thulanicreatives.stackoverflow.util.ConnectivityObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


class ConnectionViewModel (private val connectivityObserver: ConnectivityObserver) : ViewModel() {

    val isConnected = connectivityObserver.isConnected.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000L), false
    )
}

class NetworkViewModelFactory(
    private val observer: ConnectivityObserver
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ConnectionViewModel(observer) as T
    }
}