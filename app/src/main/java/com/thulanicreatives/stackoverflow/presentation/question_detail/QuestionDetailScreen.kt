package com.thulanicreatives.stackoverflow.presentation.question_detail

import android.view.ViewGroup
import android.webkit.WebView
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavBackStackEntry

@Composable
fun QuestionDetailScreen(backStackStack: NavBackStackEntry) {
    val url = backStackStack.arguments?.getString("url")
    QuestionDetailScreenWebView(url)
}

@Composable
fun QuestionDetailScreenWebView(url: String?) {
    val url = "https://stackoverflow.com/questions/56435510/presenting-modal-in-ios-13-fullscreen" ?: "https://stackoverflow.com/questions/56435510/presenting-modal-in-ios-13-fullscreen"
    // Declare a string that contains a url
   // val mUrl = "https://stackoverflow.com/questions/56435510/presenting-modal-in-ios-13-fullscreen"

    // Adding a WebView inside AndroidView
    // with layout as full screen
    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    }, update = {
        it.loadUrl(url)
    })
}

@Preview
@Composable
fun QuestionDetailScreenPreview() {
    "https://stackoverflow.com/questions/56435510/presenting-modal-in-ios-13-fullscreen"
    //QuestionDetailScreen(uri)
}