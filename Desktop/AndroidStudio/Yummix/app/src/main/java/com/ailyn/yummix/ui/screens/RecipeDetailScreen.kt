// ui/screens/RecipeDetailScreen.kt
package com.ailyn.yummix.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import com.ailyn.yummix.R
import com.ailyn.yummix.model.Category
import com.ailyn.yummix.model.Recipe
import com.ailyn.yummix.model.RecipeRepository
import com.ailyn.yummix.ui.theme.*
import androidx.compose.material3.Icon
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset

@Composable
fun RecipeDetailScreen(
    recipeId: Int,
    onBack: () -> Unit
) {
    // Obtener receta por ID
    val recipe = RecipeRepository.allRecipes.find { it.id == recipeId } ?: return

    // Estado: pestaña activa
    var selectedTab by remember { mutableStateOf(0) }

    // Detectar si es tablet
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    val isTablet = screenWidthDp >= 600

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
    ) {
        // Botón de retroceso (esquina superior izquierda)
        Box(
            modifier = Modifier
                .padding(16.dp)
                .clickable { onBack() }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icono_regresar),
                contentDescription = "Volver",
                tint = Black,
                modifier = Modifier.size(24.dp)
            )
        }

        // Imagen del plato (arriba)
        androidx.compose.foundation.Image(
            painter = painterResource(id = recipe.imageRes),
            contentDescription = recipe.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(if (isTablet) 100.dp else 250.dp)
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(20.dp))
        )

        // Contenido debajo de la imagen
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = if (isTablet) 320.dp else 260.dp) // ajuste según altura de imagen
                .padding(horizontal = 16.dp)
                .padding(bottom = 80.dp) // espacio para barra inferior
        ) {
            // Nombre de la receta
            Text(
                text = recipe.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Categoría con círculo de color
            Row(verticalAlignment = Alignment.CenterVertically) {
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

            Spacer(modifier = Modifier.height(16.dp))

            // Pestañas: Ingredientes / Preparación
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = White,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                        color = AppImportant, // línea amarilla
                        height = 3.dp
                    )
                }
            ) {
                Tab(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    text = {
                        Text(
                            text = "Ingredientes",
                            fontSize = 16.sp,
                            fontWeight = if (selectedTab == 0) FontWeight.Bold else FontWeight.Normal,
                            color = if (selectedTab == 0) Black else Color.Gray
                        )
                    }
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    text = {
                        Text(
                            text = "Preparación",
                            fontSize = 16.sp,
                            fontWeight = if (selectedTab == 1) FontWeight.Bold else FontWeight.Normal,
                            color = if (selectedTab == 1) Black else Color.Gray
                        )
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Contenido según pestaña
            if (selectedTab == 0) {
                // Ingredientes
                LazyColumn {
                    item {
                        Text(
                            text = "Para una porción:",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Black
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    items(recipe.ingredients.size) { index ->
                        Text(
                            text = "- ${recipe.ingredients[index]}",
                            fontSize = 16.sp,
                            color = Black
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                }
            } else {
                // Preparación
                LazyColumn {
                    items(recipe.steps.size) { index ->
                        Row(
                            verticalAlignment = Alignment.Top,
                            modifier = Modifier.padding(vertical = 4.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .background(
                                        when (recipe.category) {
                                            Category.BREAKFAST_DINNER -> BreakfastDinner
                                            Category.LUNCH -> Lunch
                                            Category.DRINK -> Drinks
                                            Category.DESSERT -> Desserts
                                        }
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "${index + 1}",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = White
                                )
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = recipe.steps[index],
                                fontSize = 16.sp,
                                color = Black,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }
        }

        // Barra inferior con ícono de Home
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(60.dp)
                .background(White)
                .clickable { /* Opcional: volver al inicio */ },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.inicio_inferior),
                contentDescription = "Home",
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

// Previews
@Preview(showBackground = true, name = "Receta - Celular")
@Composable
fun RecipeDetailScreenPreview_Cellular() {
    YummixTheme {
        RecipeDetailScreen(
            recipeId = 1,
            onBack = {}
        )
    }
}

@Preview(showBackground = true, name = "Receta - Tablet", widthDp = 800)
@Composable
fun RecipeDetailScreenPreview_Tablet() {
    YummixTheme {
        RecipeDetailScreen(
            recipeId = 1,
            onBack = {}
        )
    }
}