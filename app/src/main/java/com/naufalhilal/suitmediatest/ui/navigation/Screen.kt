package com.naufalhilal.suitmediatest.ui.navigation

sealed class Screen (val route:String) {
    object SplashScreen : Screen("splashScreen")
    object FirstScreen : Screen("firstScreen")
    object SecondScreen : Screen("secondScreen/{name}"){
        fun createRoute(name:String)="secondScreen/$name"
    }
    object ThirdScreen : Screen("thirdScreen")
}