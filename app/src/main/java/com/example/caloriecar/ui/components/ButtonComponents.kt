package com.example.capstone.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.caloriecar.ui.navigation.Screen
import com.example.caloriecar.ui.theme.Poppins
import com.example.caloriecar.ui.theme.md_theme_light_primary
import com.example.caloriecar.ui.theme.md_theme_light_secondary

@Composable
fun ButtonComponent(label: String, onButtonClicked: () -> Unit, isEnabled: Boolean = false) {
    OutlinedButton(
        onClick = onButtonClicked,
        modifier = Modifier.fillMaxWidth().heightIn(48.dp),
        contentPadding = PaddingValues(),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        enabled = !isEnabled
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().heightIn(48.dp).background(
                brush = Brush.horizontalGradient(
                    listOf(
                        md_theme_light_secondary, md_theme_light_primary
                    )
                ),
                shape = RoundedCornerShape(10.dp)
            ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = label,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White,
                fontFamily = Poppins
            )
        }
    }
}

@Composable
fun FABM3(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    SmallFloatingActionButton(onClick = { navController.navigate(Screen.Add.route) }) {
        Icon(imageVector = Icons.Default.Add, contentDescription = null)
    }
}