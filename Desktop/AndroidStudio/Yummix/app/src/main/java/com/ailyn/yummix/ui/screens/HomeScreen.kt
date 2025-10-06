package com.ailyn.yummix.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ailyn.yummix.R
import com.ailyn.yummix.ui.theme.Black
import com.ailyn.yummix.ui.theme.White
import com.ailyn.yummix.ui.theme.YummixTheme
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.layout.ContentScale
import com.ailyn.yummix.ui.theme.AppImportant

@Composable
fun HomeScreen(
    onNavigateToCategories: () -> Unit // 👈 Cambiado aquí
) {
    YummixTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = White)
        ) {
            // Imagen de fondo pegada al borde superior
            Image(
                painter = painterResource(id = R.drawable.imagen_inicio),
                contentDescription = "Imagen de fondo",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .align(Alignment.TopCenter)
            )

            // Logo + Texto "Yummix" en el centro
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 220.dp)
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo Yummix",
                        modifier = Modifier.size(60.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Yummix",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Black
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Botón "Empieza ahora"
                Button(
                    onClick = onNavigateToCategories, // 👈 Cambiado aquí
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AppImportant,
                        contentColor = White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Text(
                        text = stringResource(R.string.button_start),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            // Decoración inferior (onda verde)
            Image(
                painter = painterResource(id = R.drawable.barra_inferiorinicio),
                contentDescription = "Imagen de fondo",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    YummixTheme {
        HomeScreen(onNavigateToCategories = {}) // 👈 Cambiado aquí
    }
}

