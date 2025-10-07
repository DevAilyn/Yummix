package com.ailyn.yummix.navigation

// Define las rutas de navegación de la app

object Screens {
    const val HOME = "home"
    const val CATEGORIES = "categories"
    const val RECIPE_DETAIL = "recipe_detail"

    // Genera la ruta dinámica para el detalle de una receta
    fun recipeDetailRoute(recipeId: Int) = "$RECIPE_DETAIL/$recipeId"
}
