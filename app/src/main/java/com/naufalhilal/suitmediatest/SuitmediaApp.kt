package com.naufalhilal.suitmediatest

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.naufalhilal.suitmediatest.ui.navigation.Screen
import com.naufalhilal.suitmediatest.ui.screen.first_screen.FirstScreen
import com.naufalhilal.suitmediatest.ui.screen.second_screen.SecondScreen
import com.naufalhilal.suitmediatest.ui.screen.splash_screen.SplashScreen
import com.naufalhilal.suitmediatest.ui.screen.third_screen.ThirdScreen

@Composable
fun SuitmediaApp(
    modifier: Modifier=Modifier,
    navController: NavHostController= rememberNavController(),
) {
    NavHost(navController = navController,
        startDestination = Screen.SplashScreen.route,
            modifier = modifier.padding()
        ){
        composable(Screen.SplashScreen.route){
            SplashScreen(navigateToFirst = {navController.navigate(Screen.FirstScreen.route )})
        }
        composable(Screen.FirstScreen.route){
            FirstScreen(navigateToSecond = {name->navController.navigate(Screen.SecondScreen.createRoute(name=name))})
        }
        composable(Screen.SecondScreen.route, arguments = listOf(navArgument("name"){type= NavType.StringType})){
            val name= it.arguments?.getString("name")?: "no name"
            SecondScreen(name = name, navigateBack = {navController.navigateUp()}, navigateToThird = {navController.navigate(Screen.ThirdScreen.route)})
        }
        composable(Screen.ThirdScreen.route){
            ThirdScreen(navigateBack = { navController.navigateUp() })
        }
    }
}