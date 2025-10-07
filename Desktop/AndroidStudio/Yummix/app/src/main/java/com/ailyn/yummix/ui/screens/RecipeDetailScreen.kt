package com.ailyn.yummix.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ailyn.yummix.R
import com.ailyn.yummix.model.Category
import com.ailyn.yummix.model.RecipeRepository
import com.ailyn.yummix.ui.theme.*

@Composable
fun RecipeDetailScreen(
    recipeId: Int,
    onBack: () -> Unit
) {
    val recipe = RecipeRepository.allRecipes.find { it.id == recipeId } ?: return

    var selectedTab by remember { mutableStateOf(0) }

    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    val isTablet = screenWidthDp >= 600

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
    ) {


        Image(
            painter = painterResource(id = R.drawable.icono_regresar), // tu imagen PNG en drawable
            contentDescription = "Volver",
            modifier = Modifier
                .padding(top = 40.dp, start = 20.dp)
                .size(if (isTablet) 48.dp else 36.dp)
                .align(Alignment.TopStart)
                .clickable { onBack() },
            contentScale = ContentScale.Fit
        )


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = if (isTablet) 60.dp else 50.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = painterResource(id = recipe.imageRes),
                contentDescription = recipe.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(if (isTablet) 280.dp else 220.dp)
                    .clip(RoundedCornerShape(28.dp))
            )
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = if (isTablet) 420.dp else 320.dp,
                    start = 20.dp,
                    end = 20.dp,
                    bottom = 80.dp
                )
        ) {
            Text(
                text = recipe.name,
                fontSize = if (isTablet) 30.sp else 24.sp,
                fontWeight = FontWeight.Bold,
                color = Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Categoría con color
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .clip(CircleShape)
                        .background(
                            when (recipe.category) {
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
                    fontSize = if (isTablet) 18.sp else 14.sp,
                    color = Black
                )
            }

            Spacer(modifier = Modifier.height(16.dp))


            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = White,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                        color = AppImportant,
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
                            fontSize = if (isTablet) 18.sp else 16.sp,
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
                            fontSize = if (isTablet) 18.sp else 16.sp,
                            fontWeight = if (selectedTab == 1) FontWeight.Bold else FontWeight.Normal,
                            color = if (selectedTab == 1) Black else Color.Gray
                        )
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))


            if (selectedTab == 0) {
                LazyColumn {
                    item {
                        Text(
                            text = "Para una porción:",
                            fontSize = if (isTablet) 20.sp else 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Black
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    items(recipe.ingredients.size) { index ->
                        Text(
                            text = "- ${recipe.ingredients[index]}",
                            fontSize = if (isTablet) 18.sp else 16.sp,
                            color = Black
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                }
            } else {
                LazyColumn {
                    items(recipe.steps.size) { index ->
                        Row(
                            verticalAlignment = Alignment.Top,
                            modifier = Modifier.padding(vertical = 4.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(if (isTablet) 36.dp else 32.dp)
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
                                    fontSize = if (isTablet) 18.sp else 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = White
                                )
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = recipe.steps[index],
                                fontSize = if (isTablet) 18.sp else 16.sp,
                                color = Black,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(60.dp)
                .background(White)
                .clickable { /* opcional: volver al inicio */ },
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

