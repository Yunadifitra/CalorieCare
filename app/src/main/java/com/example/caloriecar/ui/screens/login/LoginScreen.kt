package com.example.caloriecar.ui.screens.login

import android.annotation.SuppressLint
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.capstone.ui.components.*
import com.example.caloriecar.R
import com.example.caloriecar.data.model.User
import com.example.caloriecar.data.pref.UserPreference
import com.example.caloriecar.data.pref.dataStore
import com.example.caloriecar.data.request.LoginRequest
import com.example.caloriecar.data.retrofit.ApiConfig
import com.example.caloriecar.di.Injection
import com.example.caloriecar.ui.ViewModelFactory
import com.example.caloriecar.ui.navigation.Screen
import com.example.caloriecar.ui.theme.Poppins
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginScreenViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository(
                LocalContext.current
            )
        )
    )
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val passwordVisible = remember {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    var isEnabled by remember { mutableStateOf(true) }

    val userPreference = UserPreference.getInstance(navController.context.dataStore)

    LaunchedEffect(true) {
        // Check the user's login status and navigate to the login screen if not logged in
        userPreference.getSession().onEach { userModel ->
            if (userModel.isLogin) {
                navController.navigate(Screen.Home.route)
            }
        }.launchIn(scope)

    }

    Surface(
        modifier = Modifier.fillMaxSize().background(color = Color.White).padding(28.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            NormalTextComponent(stringResource(R.string.hello))
            HeadingTextComponent(stringResource(R.string.login_screen))

            Spacer(modifier = Modifier.height(20.dp))

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
                isError = false,
                enabled = isEnabled
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
                isError = false,
                enabled = isEnabled
            )

            Spacer(modifier = Modifier.height(40.dp))

            ButtonComponent(stringResource(R.string.login), onButtonClicked = {
                isEnabled = false

                scope.launch {
                    var loginRequest = LoginRequest()
                    loginRequest.email = email
                    loginRequest.password = password
                    val client = ApiConfig.getApiService().login(email, password)

                    try {
                        val response = client.body()
                        if (client.isSuccessful) {
                            isEnabled = true
                            response?.let {
                                viewModel.saveSession(User(email, response.loginResult.token, true))
                                Log.i("LOGIN", response.message)
                                navController.navigate(Screen.Home.route)
                            }
                        } else {
                            isEnabled = true
                            Log.e("LOGIN", "Error: ${response?.message}")
                            snackbarHostState.showSnackbar(
                                message = "Login failed",
                                withDismissAction = true,
                                duration = SnackbarDuration.Short
                            )
                        }
                    } catch (e: Exception) {
                        isEnabled = true
                        Log.e("LOGIN", "Exception: ${e.message}")
                        snackbarHostState.showSnackbar(
                            message = "Login failed",
                            withDismissAction = true,
                            duration = SnackbarDuration.Short
                        )
                    }
                }

            })

            Spacer(modifier = Modifier.height(20.dp))

            ClickableLoginTextComponent(false, onTextSelected = {
                navController.navigate(Screen.Register.route)
            })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())
}