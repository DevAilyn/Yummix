// ui/components/RecipeCard.kt
package com.ailyn.yummix.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ailyn.yummix.R
import com.ailyn.yummix.model.Category
import com.ailyn.yummix.model.Recipe
import com.ailyn.yummix.ui.theme.*
import androidx.compose.foundation.background

/**
 * Tarjeta visual de una receta con imagen, nombre, categoría y botón de acción.
 */
@Composable
fun RecipeCard(
    recipe: Recipe,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(350.dp)
            .height(450.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = White)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Nombre de la receta
            Text(
                text = recipe.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Black,
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                    .align(Alignment.TopCenter)
            )

            // Imagen del plato
            androidx.compose.foundation.Image(
                painter = painterResource(id = recipe.imageRes),
                contentDescription = recipe.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(310.dp)
                    .height(300.dp)
                    .clip(RoundedCornerShape(16.dp))
            )

            // Categoría con indicador de color
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 16.dp, bottom = 16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .clip(CircleShape)
                        .background(
                            color = when (recipe.category) {
                                Category.BREAKFAST_DINNER -> BreakfastDinner
                                Category.LUNCH -> Lunch
                                Category.DRINK -> Drinks
                                Category.DESSERT -> Desserts
                            }
                        )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = when (recipe.category) {
                        Category.BREAKFAST_DINNER -> "Desayuno - Cena"
                        Category.LUNCH -> "Almuerzo"
                        Category.DRINK -> "Bebida"
                        Category.DESSERT -> "Postre"
                    },
                    fontSize = 14.sp,
                    color = Black
                )
            }

            // Botón "Ver receta" (ícono PNG)
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(12.dp)
                    .clickable { onClick() },
                contentAlignment = Alignment.Center
            ) {
                androidx.compose.foundation.Image(
                    painter = painterResource(id = R.drawable.ver_recetalogo),
                    contentDescription = "Ver receta",
                    modifier = Modifier.size(50.dp)
                )
            }
        }
    }
}

// Previsualizaciones por categoría
@Preview(showBackground = true, name = "Desayuno")
@Composable
fun RecipeCardPreview_Breakfast() {
    YummixTheme {
        RecipeCard(
            recipe = Recipe(
                id = 1,
                name = "Omelette de Champiñones",
                imageRes = R.drawable.omelette_champinones,
                category = Category.BREAKFAST_DINNER,
                timeMinutes = 15,
                ingredients = emptyList(),
                steps = emptyList()
            ),
            onClick = {}
        )
    }
}

@Preview(showBackground = true, name = "Almuerzo")
@Composable
fun RecipeCardPreview_Lunch() {
    YummixTheme {
        RecipeCard(
            recipe = Recipe(
                id = 2,
                name = "Tallarines con Carne",
                imageRes = R.drawable.tallarines_carne,
                category = Category.LUNCH,
                timeMinutes = 25,
                ingredients = emptyList(),
                steps = emptyList()
            ),
            onClick = {}
        )
    }
}

@Preview(showBackground = true, name = "Bebida")
@Composable
fun RecipeCardPreview_Drink() {
    YummixTheme {
        RecipeCard(
            recipe = Recipe(
                id = 3,
                name = "Limonada con Hierbabuena",
                imageRes = R.drawable.limonada_hierbabuena,
                category = Category.DRINK,
                timeMinutes = 5,
                ingredients = emptyList(),
                steps = emptyList()
            ),
            onClick = {}
        )
    }
}

@Preview(showBackground = true, name = "Postre")
@Composable
fun RecipeCardPreview_Dessert() {
    YummixTheme {
        RecipeCard(
            recipe = Recipe(
                id = 4,
                name = "Cheesecake de Frutos Rojos",
                imageRes = R.drawable.cheese_frutosrojos,
                category = Category.DESSERT,
                timeMinutes = 60,
                ingredients = emptyList(),
                steps = emptyList()
            ),
            onClick = {}
        )
    }
}