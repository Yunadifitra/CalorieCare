package com.example.caloriecar.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.caloriecar.R
import com.example.caloriecar.ui.theme.Poppins

@Composable
fun MyTextFieldComponent(
    labelValue: String,
    painterResource: Painter,
    errorStatus: Boolean = false,
    isEmail: Boolean = false,
    textValue: String
) {
    val localFocusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(25),
        label = { Text(text = labelValue, fontFamily = Poppins, fontWeight = FontWeight.Normal, fontSize = 12.sp) },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = if (isEmail) KeyboardType.Email else KeyboardType.Text
        ),
        textStyle = TextStyle(fontWeight = FontWeight.Normal, fontSize = 18.sp),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            focusedBorderColor = Color.LightGray,
            focusedLabelColor = Color.DarkGray
        ),
        maxLines = 1,
        value = textValue,
        onValueChange = { newText ->

        },
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        isError = errorStatus
    )
}

@Preview(showBackground = true)
@Composable
fun ComponentPreview() {
    MyTextFieldComponent("First Name", painterResource(R.drawable.ic_profile), textValue = ""
    )
}