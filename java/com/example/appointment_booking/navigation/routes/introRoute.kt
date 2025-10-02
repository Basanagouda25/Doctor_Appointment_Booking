package com.example.appointment_booking.navigation.routes

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.appointment_booking.feature.intro.IntroScreen
import com.example.appointment_booking.navigation.Screen

fun NavGraphBuilder.introRoute(onStart:()-> Unit){
    composable(Screen.Intro.route){
        IntroScreen(onStartedClick = onStart)

    }
}