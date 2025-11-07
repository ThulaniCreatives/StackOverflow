package com.thulanicreatives.stackoverflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.thulanicreatives.stackoverflow.presentation.component.HeaderSection
import com.thulanicreatives.stackoverflow.presentation.question_detail.QuestionDetailScreen
import com.thulanicreatives.stackoverflow.presentation.question_list.MainScreen
import com.thulanicreatives.stackoverflow.ui.theme.StackOverflowTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

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
                        startDestination = "SearchScreen",
                    ) {
                        composable(
                            route = "SearchScreen"
                        ) {
                            MainScreen(navController = navController)
                        }
                        composable(
                            route = "QuestionDetail/{url}",
                            arguments = listOf(
                                navArgument(name = "url") { type = NavType.StringType })
                        ) { backStackEntry ->

                            QuestionDetailScreen(backStackEntry)
                        }
                    }
                    innerPadding.calculateTopPadding()
                }
            }
        }
    }
}

@Serializable
object SearchScreen