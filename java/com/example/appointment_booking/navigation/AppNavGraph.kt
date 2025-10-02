package com.example.appointment_booking.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.appointment_booking.core.ViewModel.MainViewModel
import com.example.appointment_booking.navigation.Screen
import com.example.appointment_booking.navigation.routes.detailRoute
import com.example.appointment_booking.navigation.routes.homeRoute
import com.example.appointment_booking.navigation.routes.introRoute
import com.example.appointment_booking.navigation.routes.topDoctorsRoute


@Composable
fun AppNavGraph(
    nav : NavHostController,
    vm: MainViewModel
){
    NavHost(navController = nav,startDestination = Screen.Intro.route){
        introRoute(
                onStart = {
                    nav.navigate(Screen.Home.route){
                        popUpTo(Screen.Intro.route){
                            inclusive = true
                        }
                    }
                }
        )
        homeRoute(vm = vm,
            onOpenDetail = {doctorModel -> nav.navigateToDetail(doctorModel)},
            onOpenTopDoctors = {nav.navigate(Screen.TopDoctors.route)}
        )

        topDoctorsRoute(
            vm = vm,
            {nav.popBackStack()},
            onOpenDetail = {doctorModel -> nav.navigateToDetail(doctorModel)}
        )

        detailRoute(
            nav = nav,
            onBack = {nav.popBackStack()}
        )

    }
}