package com.example.caloriecar.ui.screens.register

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.caloriecar.R
import com.example.caloriecar.data.retrofit.ApiConfig
import com.example.caloriecar.ui.navigation.Screen
import com.example.caloriecar.ui.theme.Poppins
import com.example.capstone.ui.components.*
import com.google.gson.JsonObject
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val passwordVisible = remember {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Surface(
        modifier = Modifier.fillMaxSize().background(color = Color.White).padding(28.dp),
    ) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            NormalTextComponent(stringResource(R.string.hello))
            HeadingTextComponent(stringResource(R.string.register_screen))

            Spacer(modifier = Modifier.height(20.dp))

            // FIRST NAME INPUT
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(25),
                label = {
                    Text(
                        text = stringResource(R.string.first_name),
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp
                    )
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                ),
                textStyle = TextStyle(fontWeight = FontWeight.Normal, fontSize = 18.sp),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    focusedBorderColor = Color.LightGray,
                    focusedLabelColor = Color.DarkGray
                ),
                maxLines = 1,
                value = firstName,
                onValueChange = { newText ->
                    firstName = newText
                },
                leadingIcon = {
                    Icon(painter = painterResource(R.drawable.ic_profile), contentDescription = "email_icon")
                },
                isError = false
            )

            // LAST NAME INPUT
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(25),
                label = {
                    Text(
                        text = stringResource(R.string.last_name),
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp
                    )
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                ),
                textStyle = TextStyle(fontWeight = FontWeight.Normal, fontSize = 18.sp),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    focusedBorderColor = Color.LightGray,
                    focusedLabelColor = Color.DarkGray
                ),
                maxLines = 1,
                value = lastName,
                onValueChange = { newText ->
                    lastName = newText
                },
                leadingIcon = {
                    Icon(painter = painterResource(R.drawable.ic_profile), contentDescription = "email_icon")
                },
                isError = false
            )

            // EMAIL INPUT
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(25),
                label = {
                    Text(
                        text = stringResource(R.string.email),
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp
                    )
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Email
                ),
                textStyle = TextStyle(fontWeight = FontWeight.Normal, fontSize = 18.sp),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    focusedBorderColor = Color.LightGray,
                    focusedLabelColor = Color.DarkGray
                ),
                maxLines = 1,
                value = email,
                onValueChange = { newText ->
                    email = newText
                },
                leadingIcon = {
                    Icon(painter = painterResource(R.drawable.ic_message), contentDescription = "email_icon")
                },
                isError = false
            )

            // PASSWORD INPUT
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(25),
                label = {
                    Text(
                        text = stringResource(R.string.password),
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                textStyle = TextStyle(fontWeight = FontWeight.Normal, fontSize = 18.sp),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    focusedBorderColor = Color.LightGray,
                    focusedLabelColor = Color.DarkGray
                ),
                maxLines = 1,
                value = password,
                onValueChange = { newText ->
                    password = newText
                },
                leadingIcon = {
                    Icon(painter = painterResource(R.drawable.ic_lock), contentDescription = "password_icon")
                },
                trailingIcon = {

                    val iconImage = if (passwordVisible.value) {
                        Icons.Filled.Visibility
                    } else {
                        Icons.Filled.VisibilityOff
                    }

                    val description = if (passwordVisible.value) {
                        stringResource(id = R.string.hide_password)
                    } else {
                        stringResource(id = R.string.show_password)
                    }

                    IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                        Icon(imageVector = iconImage, contentDescription = description)
                    }

                },
                visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                isError = false
            )
            CheckboxComponent(value = stringResource(id = R.string.terms_and_condition),
                onTextSelected = {

                },
                onCheckedChange = {

                }
            )

            Spacer(modifier = Modifier.height(40.dp))

            ButtonComponent(stringResource(R.string.register), onButtonClicked = {
                scope.launch {
                    val client =
                        ApiConfig.getApiService().register(
                            JsonObject().apply {
                                addProperty("name", firstName)
                                addProperty("email", email)
                                addProperty("password", password)
                            }
                        )

                    try {
                        val response = client.body()
                        if (client.isSuccessful) {
                            response?.let {
                                navController.navigate(Screen.Login.route)
                            }
                        } else {
                            Log.e("REGISTER", "Error: ${client.code()}")
                            snackbarHostState.showSnackbar(
                                message = "REGISTER failed",
                                withDismissAction = true,
                                duration = SnackbarDuration.Short
                            )
                        }
                    } catch (e: Exception) {
                        Log.e("REGISTER", "Exception: ${e.message}")
                        snackbarHostState.showSnackbar(
                            message = "REGISTER failed",
                            withDismissAction = true,
                            duration = SnackbarDuration.Short
                        )
                    }

                }
            })

            Spacer(modifier = Modifier.height(20.dp))

            ClickableLoginTextComponent(true, onTextSelected = {
                navController.navigate(Screen.Login.route)
            })
        }
    }
}


@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(navController = rememberNavController())
}