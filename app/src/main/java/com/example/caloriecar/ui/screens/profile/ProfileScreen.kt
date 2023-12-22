package com.example.caloriecar.ui.screens.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.caloriecar.R
import com.example.caloriecar.di.Injection
import com.example.caloriecar.ui.ViewModelFactory
import com.example.caloriecar.ui.navigation.NavigationItem
import com.example.caloriecar.ui.navigation.Screen
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileScreenViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))),
    navController: NavController
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    var name by rememberSaveable { mutableStateOf("") }

    val items = listOf(
        NavigationItem(
            title = stringResource(R.string.menu_home),
            icon = Icons.Default.Home,
            screen = Screen.Home
        ),
        NavigationItem(
            title = stringResource(R.string.menu_profile),
            icon = Icons.Default.AccountCircle,
            screen = Screen.Profile
        ),
    )
    val selectedItem = remember { mutableStateOf(items[1]) }

    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(12.dp))
                items.forEach { item ->
                    NavigationDrawerItem(
                        icon = { Icon(item.icon, contentDescription = null) },
                        label = { Text(item.title) },
                        selected = item == selectedItem.value,
                        onClick = {
                            navController.navigate(item.screen.route)
                            selectedItem.value = item
                        },
                        modifier = modifier.padding(horizontal = 12.dp)
                    )
                }
                NavigationDrawerItem(
                    icon = { Icon(imageVector = Icons.Outlined.Logout, contentDescription = null) },
                    label = { Text(text = "Logout") },
                    selected = false,
                    onClick = {
                        viewModel.logout()
                    },
                    modifier = modifier.padding(horizontal = 12.dp)
                )
            }
        },
        gesturesEnabled = true
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(8.dp)
                .verticalScroll(rememberScrollState())
        ) {
            TopBar(openDrawer = {
                scope.launch {
                    drawerState.apply {
                        if (isClosed) open() else close()
                    }
                }
            })

            Spacer(Modifier.height(24.dp))

            Row(
                modifier = modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Name", modifier = modifier.width(100.dp), fontWeight = FontWeight.Normal, fontSize = 14.sp)
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedTextColor = Color.Black
                    ),
                    maxLines = 1,
                    singleLine = true
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(modifier: Modifier = Modifier, openDrawer: () -> Unit) {
    var showMenu by remember { mutableStateOf(false) }
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = openDrawer) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
            }
        },
        title = {
            Text(text = "Profile")
        },
    )
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    ProfileScreen(navController = rememberNavController())
}