package com.example.glucode_getitdone_to_do_list

enum class ToDoWeatherScreens {
    HomeScreen,
    SplashScreen;

    companion object {
        fun fromRoute(route:String?): ToDoWeatherScreens
                = when(route?.substringBefore("/")){
            HomeScreen.name -> HomeScreen
            SplashScreen.name -> SplashScreen
            null -> HomeScreen
            else -> throw java.lang.IllegalArgumentException("Route $route is not recognized")
        }
    }
}