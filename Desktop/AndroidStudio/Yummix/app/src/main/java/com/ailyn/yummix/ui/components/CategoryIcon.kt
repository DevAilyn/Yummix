// ui/components/CategoryIcon.kt
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
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CategoryIcon(
    category: Category,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = when {
        isSelected -> when (category) {
            Category.BREAKFAST_DINNER -> BreakfastDinner
            Category.LUNCH -> Lunch
            Category.DRINK -> Drinks
            Category.DESSERT -> Desserts
        }
        else -> White
    }

    val iconTint = if (isSelected) Black else Black

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
            tint = iconTint,
            modifier = Modifier.size(40.dp)
        )
    }
}


// PREVIEW

@Preview(showBackground = true, name = "Desayuno - Seleccionado")
@Composable
fun CategoryIconPreview_Breakfast_Selected() {
    YummixTheme {
        CategoryIcon(
            category = Category.BREAKFAST_DINNER,
            isSelected = true,
            onClick = {}
        )
    }
}

@Preview(showBackground = true, name = "Desayuno - No seleccionado")
@Composable
fun CategoryIconPreview_Breakfast_Unselected() {
    YummixTheme {
        CategoryIcon(
            category = Category.BREAKFAST_DINNER,
            isSelected = false,
            onClick = {}
        )
    }
}

@Preview(showBackground = true, name = "Almuerzo - Seleccionado")
@Composable
fun CategoryIconPreview_Lunch_Selected() {
    YummixTheme {
        CategoryIcon(
            category = Category.LUNCH,
            isSelected = true,
            onClick = {}
        )
    }
}

@Preview(showBackground = true, name = "Bebida - Seleccionado")
@Composable
fun CategoryIconPreview_Drink_Selected() {
    YummixTheme {
        CategoryIcon(
            category = Category.DRINK,
            isSelected = true,
            onClick = {}
        )
    }
}

@Preview(showBackground = true, name = "Postre - Seleccionado")
@Composable
fun CategoryIconPreview_Dessert_Selected() {
    YummixTheme {
        CategoryIcon(
            category = Category.DESSERT,
            isSelected = true,
            onClick = {}
        )
    }
}