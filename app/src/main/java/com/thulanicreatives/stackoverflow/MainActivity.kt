package com.thulanicreatives.stackoverflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.thulanicreatives.stackoverflow.domain.model.CustomNavType
import com.thulanicreatives.stackoverflow.domain.model.QuestionDetailData
import com.thulanicreatives.stackoverflow.presentation.component.HeaderSection
import com.thulanicreatives.stackoverflow.presentation.question_detail.QuestionDetailScreen
import com.thulanicreatives.stackoverflow.presentation.question_list.HomeScreenRoute
import com.thulanicreatives.stackoverflow.presentation.question_list.MainScreen
import com.thulanicreatives.stackoverflow.presentation.question_list.QuestionDetailRoute
import com.thulanicreatives.stackoverflow.presentation.question_list.StackoverflowViewModel
import com.thulanicreatives.stackoverflow.ui.theme.StackOverflowTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StackOverflowTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 24.dp), topBar = {
                        HeaderSection()
                    }

                ) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = HomeScreenRoute
                    ) {
                        composable<HomeScreenRoute> {
                            MainScreen(
                                onItemClicked = { questionDetailData ->
                                    navController.navigate(
                                        QuestionDetailRoute(questionDetailData = questionDetailData)
                                    )
                                })
                        }
                        composable<QuestionDetailRoute>(
                            typeMap = mapOf(typeOf<QuestionDetailData>() to CustomNavType.QuestionDetailDataType)
                        ) { backStackEntry ->
                            val arguments = backStackEntry.toRoute<QuestionDetailRoute>()
                            QuestionDetailScreen(questionDetailData = arguments.questionDetailData)
                        }
                    }
                    innerPadding.calculateTopPadding()
                }
            }
        }
    }
}