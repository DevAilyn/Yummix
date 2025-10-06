package com.ailyn.yummix.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ailyn.yummix.ui.screens.*

@Composable
fun YummixNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screens.HOME,
        modifier = modifier
    ) {
        composable(Screens.HOME) {
            HomeScreen(
                onNavigateToCategories = {
                    navController.navigate(Screens.CATEGORIES)
                }
            )
        }

//        composable(Screens.CATEGORIES) {
//            CategoriesScreen(
//                onNavigateToBreakfastDinner = {
//                    navController.navigate(Screens.BREAKFAST_DINNER)
//                },
//                onNavigateToLunch = {
//                    navController.navigate(Screens.LUNCH)
//                },
//                onNavigateToDrinks = {
//                    navController.navigate(Screens.DRINKS)
//                },
//                onNavigateToDesserts = {
//                    navController.navigate(Screens.DESSERTS)
//                }
//            )
//        }
//
//        composable(Screens.BREAKFAST_DINNER) {
//            BreakfastDinnerScreen(
//                onBack = { navController.popBackStack() }
//            )
//        }
//
//        composable(Screens.LUNCH) {
//            LunchScreen(
//                onBack = { navController.popBackStack() }
//            )
//        }
//
//        composable(Screens.DRINKS) {
//            DrinksScreen(
//                onBack = { navController.popBackStack() }
//            )
//        }
//
//        composable(Screens.DESSERTS) {
//            DessertsScreen(
//                onBack = { navController.popBackStack() }
//            )
//        }
    }
}


