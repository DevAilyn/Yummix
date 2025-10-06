// model/Recipe.kt
package com.ailyn.yummix.model

import androidx.annotation.DrawableRes

data class Recipe(
    val id: Int,
    val name: String,
    @DrawableRes val imageRes: Int,
    val category: Category,
    val timeMinutes: Int,
    val ingredients: List<String>,
    val steps: List<String>
)