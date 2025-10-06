// ui/screens/CategoriesScreen.kt
package com.ailyn.yummix.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.ui.platform.LocalConfiguration
import com.ailyn.yummix.R
import com.ailyn.yummix.model.Category
import com.ailyn.yummix.model.RecipeRepository
import com.ailyn.yummix.ui.components.CategoryIcon
import com.ailyn.yummix.ui.components.RecipeCard
import com.ailyn.yummix.ui.theme.*

@Composable
fun CategoriesScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToRecipeDetail: (Int) -> Unit
) {
    // Estado: categoría seleccionada (null = "Todas")
    var selectedCategory by remember { mutableStateOf<Category?>(null) }

    // Obtener recetas según categoría
    val recipes = RecipeRepository.getRecipesByCategory(selectedCategory)

    // Detectar si es tablet o celular
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    val isTablet = screenWidthDp >= 600 // Ajusta este valor según tu definición de tablet

    // Número de columnas: 2 si es tablet y "Todo", 1 si es celular o categoría específica
    val columns = if (selectedCategory == null && isTablet) 2 else 1

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
    ) {
        // Imagen decorativa superior (pegada al borde)
        androidx.compose.foundation.Image(
            painter = painterResource(id = R.drawable.barrasuperior_categorias),
            contentDescription = "Decoración superior",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .align(Alignment.TopCenter)
        )

        // Título y subtítulo
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 24.dp, end = 24.dp)
                .align(Alignment.TopStart)
        ) {
            Text(
                text = "Bienvenido",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "¿Qué deseas preparar?",
                fontSize = 24.sp,
                fontWeight = FontWeight.Normal,
                color = Black
            )
        }

        // Barra de íconos de categorías
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 120.dp) // Ajusta según la imagen superior
                .height(60.dp)
        ) {
            // Botón "Todo"
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(if (selectedCategory == null) AppAccent else White)
                    .clickable { selectedCategory = null }
            ) {
                Text(
                    text = "Todo",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (selectedCategory == null) Color.White else Color.Black
                )
            }

            // Íconos de categorías
            CategoryIcon(
                category = Category.BREAKFAST_DINNER,
                isSelected = selectedCategory == Category.BREAKFAST_DINNER,
                onClick = { selectedCategory = Category.BREAKFAST_DINNER }
            )

            CategoryIcon(
                category = Category.LUNCH,
                isSelected = selectedCategory == Category.LUNCH,
                onClick = { selectedCategory = Category.LUNCH }
            )

            CategoryIcon(
                category = Category.DRINK,
                isSelected = selectedCategory == Category.DRINK,
                onClick = { selectedCategory = Category.DRINK }
            )

            CategoryIcon(
                category = Category.DESSERT,
                isSelected = selectedCategory == Category.DESSERT,
                onClick = { selectedCategory = Category.DESSERT }
            )
        }

        // Grid de recetas (2x2 en tablet, 1 columna en celular)
        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 200.dp, bottom = 70.dp) // espacio para barra superior e inferior
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(recipes.size) { index ->
                RecipeCard(
                    recipe = recipes[index],
                    onClick = { onNavigateToRecipeDetail(recipes[index].id) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        // Barra inferior con ícono de Home
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(60.dp)
                .background(White)
                .clickable { onNavigateToHome() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.inicio_inferior),
                contentDescription = "Home",
                tint = Color.Black,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}