package com.ailyn.yummix.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ailyn.yummix.R
import com.ailyn.yummix.model.Category
import com.ailyn.yummix.ui.theme.*

@Composable
fun CategoryIcon(
    category: Category,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (isSelected) {
        when (category) {
            Category.BREAKFAST_DINNER -> BreakfastDinner
            Category.LUNCH -> Lunch
            Category.DRINK -> Drinks
            Category.DESSERT -> Desserts
        }
    } else {
        White
    }

    Box(
        modifier = modifier
            .size(60.dp)
            .clip(CircleShape)
            .background(backgroundColor)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(
                when (category) {
                    Category.BREAKFAST_DINNER -> R.drawable.icono_desaycenas
                    Category.LUNCH -> R.drawable.icono_almuerzos
                    Category.DRINK -> R.drawable.icono_bebidas
                    Category.DESSERT -> R.drawable.icono_postres
                }
            ),
            contentDescription = category.name,
            tint = if (isSelected) Black else Black,
            modifier = Modifier.size(40.dp)
        )
    }
}