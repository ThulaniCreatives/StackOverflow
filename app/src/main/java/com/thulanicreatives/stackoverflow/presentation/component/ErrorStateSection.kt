package com.thulanicreatives.stackoverflow.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.thulanicreatives.stackoverflow.R
import com.thulanicreatives.stackoverflow.presentation.network_connectivity.ConnectionViewModel
import com.thulanicreatives.stackoverflow.presentation.question_list.MainState

@Composable
fun NetworkStatusScreen(networkViewModel: ConnectionViewModel, mainState: MainState) {

    val isInternetAvailable by networkViewModel.isConnected.collectAsState()
    if (isInternetAvailable) {
        if (!mainState.isLoading && mainState.questionResults == null) {
            ErrorView(stringResource(R.string.connected))
        }
    } else {
        ErrorView(stringResource(R.string.not_connected))
    }
}

@Composable
fun ErrorView(message: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {
        Image(
            imageVector = Icons.Rounded.Info,
            contentDescription = null,
            modifier = Modifier.size(108.dp)
        )
        Spacer(modifier = Modifier.padding(16.dp))
        ErrorText(message)
    }
}

@Composable
fun ErrorText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 48.dp),
        style = MaterialTheme.typography.bodySmall,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.error.copy(alpha = 0.9F)
    )
}
