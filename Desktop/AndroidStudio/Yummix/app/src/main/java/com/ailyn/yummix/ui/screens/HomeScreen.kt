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
import androidx.compose.ui.layout.ContentScale
import com.ailyn.yummix.R
import com.ailyn.yummix.ui.theme.Black
import com.ailyn.yummix.ui.theme.White
import com.ailyn.yummix.ui.theme.AppImportant
import com.ailyn.yummix.ui.theme.YummixTheme

@Composable
fun HomeScreen(onNavigateToCategories: () -> Unit) {
    YummixTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(White)
        ) {
            Image(
                painter = painterResource(id = R.drawable.imagen_inicio),
                contentDescription = "Fondo",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .align(Alignment.TopCenter)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 220.dp)
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo",
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

                Button(
                    onClick = onNavigateToCategories,
                    colors = ButtonDefaults.buttonColors(containerColor = AppImportant, contentColor = White),
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

            Image(
                painter = painterResource(id = R.drawable.barra_inferiorinicio),
                contentDescription = "Decoración inferior",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .align(Alignment.BottomCenter)
            )
        }
    }
}
