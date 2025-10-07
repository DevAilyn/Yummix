// model/RecipeRepository.kt
package com.ailyn.yummix.model

import com.ailyn.yummix.R

// Repositorio estático con todas las recetas de la app

object RecipeRepository {
    val allRecipes = listOf(
        // Desayunos y Cenas
        Recipe(
            id = 1,
            name = "Omelette de Champiñones",
            imageRes = R.drawable.omelette_champinones,
            category = Category.BREAKFAST_DINNER,
            timeMinutes = 15,
            ingredients = listOf(
                "3 huevos",
                "100g champiñones",
                "1 puñado espinacas",
                "50g queso rallado",
                "Sal y pimienta al gusto"
            ),
            steps = listOf(
                "Bate los huevos en un tazón y sazona con sal y pimienta.",
                "En una sartén, saltea los champiñones hasta dorar.",
                "Agrega las espinacas y cocina 1 minuto más.",
                "Vierte los huevos batidos y cocina a fuego medio.",
                "Espolvorea el queso y dobla el omelette. ¡Listo!"
            )
        ),

        // Almuerzos
        Recipe(
            id = 2,
            name = "Tallarines con Carne",
            imageRes = R.drawable.tallarines_carne,
            category = Category.LUNCH,
            timeMinutes = 25,
            ingredients = listOf(
                "200g tallarines",
                "150g carne molida",
                "1 tomate picado",
                "1 cebolla",
                "Albahaca fresca",
                "Aceite de oliva"
            ),
            steps = listOf(
                "Cocina los tallarines según instrucciones del paquete.",
                "En una sartén, sofríe la cebolla en aceite.",
                "Agrega la carne molida y cocina hasta dorar.",
                "Incorpora el tomate y cocina 5 minutos.",
                "Mezcla con los tallarines y decora con albahaca."
            )
        ),

        // Bebidas
        Recipe(
            id = 3,
            name = "Limonada con Hierbabuena",
            imageRes = R.drawable.limonada_hierbabuena,
            category = Category.DRINK,
            timeMinutes = 5,
            ingredients = listOf(
                "4 limones",
                "4 cucharadas de azúcar",
                "10 hojas de hierbabuena",
                "1 litro de agua fría",
                "Hielo"
            ),
            steps = listOf(
                "Exprime los limones en una jarra.",
                "Agrega el azúcar y mezcla bien.",
                "Añade las hojas de hierbabuena y machácalas ligeramente.",
                "Vierte el agua y agrega hielo.",
                "Refrigera 10 minutos y sirve."
            )
        ),

        // Postres
        Recipe(
            id = 4,
            name = "Cheesecake de Frutos Rojos",
            imageRes = R.drawable.cheese_frutosrojos,
            category = Category.DESSERT,
            timeMinutes = 60,
            ingredients = listOf(
                "200g galletas digestivas",
                "100g mantequilla derretida",
                "500g queso crema",
                "150g azúcar",
                "200ml crema para batir",
                "200g frutos rojos"
            ),
            steps = listOf(
                "Tritura las galletas y mézclalas con la mantequilla.",
                "Forra el fondo de un molde y refrigera 30 min.",
                "Bate el queso crema con el azúcar hasta cremoso.",
                "Incorpora la crema batida suavemente.",
                "Vierte sobre la base, alisa y refrigera 4 horas.",
                "Decora con frutos rojos antes de servir."
            )
        )
    )

    fun getRecipesByCategory(category: Category?): List<Recipe> {
        return if (category == null) {
            allRecipes
        } else {
            allRecipes.filter { it.category == category }
        }
    }
}