package com.ailyn.yummix.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ailyn.yummix.R
import com.ailyn.yummix.model.Category
import com.ailyn.yummix.model.Recipe
import com.ailyn.yummix.ui.theme.*

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
            Text(
                text = recipe.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Black,
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                    .align(Alignment.TopCenter)
            )

            Image(
                painter = painterResource(id = recipe.imageRes),
                contentDescription = recipe.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(310.dp)
                    .height(300.dp)
                    .clip(RoundedCornerShape(16.dp))
            )

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
                    fontSize = 14.sp,
                    color = Black
                )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(12.dp)
                    .clickable { onClick() },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ver_recetalogo),
                    contentDescription = "Ver receta",
                    modifier = Modifier.size(50.dp)
                )
            }
        }
    }
}