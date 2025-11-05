package com.thulanicreatives.stackoverflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.thulanicreatives.stackoverflow.presentation.MainUIEvents
import com.thulanicreatives.stackoverflow.presentation.StackoverflowViewModel
import com.thulanicreatives.stackoverflow.ui.theme.StackOverflowTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StackOverflowTheme {

                val stackoverflowViewModel = hiltViewModel<StackoverflowViewModel>()
                val mainState by stackoverflowViewModel.mainState.collectAsState()



                Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        value = mainState.searchQuestion,
                        onValueChange = {
                            stackoverflowViewModel.onEvent(MainUIEvents.OnSearchQuestion(it))
                        },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Rounded.Search,
                                contentDescription = stringResource(R.string.search),
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier
                                    .size(30.dp)
                                    .clickable {
                                        stackoverflowViewModel.onEvent(MainUIEvents.OnSearchClick)
                                    })
                        },
                        label = {
                            Text(
                                stringResource(R.string.search),
                                fontSize = 15.sp,
                                modifier = Modifier.alpha(0.7f)
                            )
                        },
                        textStyle = TextStyle(color = MaterialTheme.colorScheme.onBackground, fontSize = 19.5.sp),
                    )
                }) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StackOverflowTheme {
        Greeting("Android")
    }
}