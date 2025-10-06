package com.ailyn.yummix.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
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
            HomeScreen(onNavigateToCategories = { navController.navigate(Screens.CATEGORIES) })
        }

        composable(Screens.CATEGORIES) {
            CategoriesScreen(
                onNavigateToHome = { navController.popBackStack(Screens.HOME, inclusive = false) },
                onNavigateToRecipeDetail = { recipeId ->
                    navController.navigate("${Screens.RECIPE_DETAIL}/$recipeId")
                }
            )
        }

        composable(
            route = "${Screens.RECIPE_DETAIL}/{recipeId}",
            arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
        ) { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getInt("recipeId") ?: return@composable
            RecipeDetailScreen(recipeId = recipeId, onBack = { navController.popBackStack() })
        }
    }
}
