package com.example.caloriecar.ui.screens.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.caloriecar.BackPressHandler
import com.example.caloriecar.R
import com.example.caloriecar.data.model.ScheduleType
import com.example.caloriecar.di.Injection
import com.example.caloriecar.ui.ViewModelFactory
import com.example.caloriecar.ui.navigation.NavigationItem
import com.example.caloriecar.ui.navigation.Screen
import com.example.caloriecar.ui.theme.*
import kotlinx.coroutines.launch

private val schedules = listOf(
    ScheduleType("Sarapan", "Bubur ayam", 100, "7AM - 8AM"),
    ScheduleType("Makan siang", "Bakso", 300, "12AM - 13AM"),
    ScheduleType("Makan malam", "Pizaa", 850, "8AM - 9AM"),
)

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier, viewModel: HomeScreenViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository(
                LocalContext.current
            )
        )
    ),
    navController: NavController
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val routes = listOf(
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
    val selectedItem = remember { mutableStateOf(routes[0]) }

    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    viewModel.getSession().observe(lifecycleOwner) { user ->
        if (!user.isLogin) {
            navController.navigate(Screen.Login.route)
        }
    }

    BackPressHandler(enabled = drawerState.isOpen) {
        scope.launch {
            drawerState.close()
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(12.dp))

                routes.forEach { item ->
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
        Scaffold(
            topBar = {
                TopBar(openDrawer = {
                    scope.launch {
                        drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                })
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
//                        context.startActivity(Intent(context, CameraActivity::class.java))
                        navController.navigate(Screen.Add.route)
                    },
                ) {
                    Icon(Icons.Filled.Add, "Floating action button.")
                }
            }
        ) { paddingValues ->
            LazyColumn(
                modifier = modifier
                    .padding(paddingValues),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    top = 16.dp,
                    bottom = 16.dp
                )
            ) {
                item {
                    GreetingSection()
                    Spacer(modifier = Modifier.height(20.dp))
                }
                item {
                    WelcomeSection()
                    Spacer(modifier = Modifier.height(20.dp))
                }

                items(schedules) { item ->
                    TaskComponent(item)
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(openDrawer: () -> Unit) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = openDrawer) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
            }
        },
        title = {

        },
        scrollBehavior = scrollBehavior
    )
}

@Composable
fun GreetingSection() {
    Column(verticalArrangement = Arrangement.spacedBy(1.dp)) {
        Text(
            text = "Hi, Dini!",
            fontFamily = Poppins,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        )
        Text(
            text = stringResource(R.string.morning),
            fontFamily = Poppins,
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun WelcomeSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp))
            .background(colorResource(R.color.light_blue))
            .height(150.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(30.dp))
                .weight(0.9f)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = stringResource(R.string.welcome),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Poppins
            )
            Text(
                text = stringResource(R.string.schedule_your_plan),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = Poppins,
                color = Color.Gray
            )
        }
        Image(
            painter = painterResource(R.drawable.login),
            contentDescription = null,
            modifier = Modifier.size(145.dp).padding(end = 30.dp)
        )
    }
}

@Composable
fun TaskComponent(task: ScheduleType) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = task.time,
            fontFamily = Poppins,
            textAlign = TextAlign.Center
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .clip(CircleShape)
                    .border(
                        border = BorderStroke(3.dp, Color.Black),
                        shape = CircleShape
                    )
            )

            Divider(modifier = Modifier.width(6.dp), color = Color.Black)

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(14.dp))
                        .background(colorResource(R.color.wheat))
                        .weight(0.9f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = task.name,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(
                            start = 12.dp,
                            top = 12.dp
                        )
                    )

                    Text(
                        text = task.foodTitle,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(start = 12.dp),
                        color = Color.Gray
                    )


                    Text(
                        text = "${task.calories}",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(
                            start = 12.dp,
                            bottom = 12.dp
                        )
                    )
                }

                Divider(
                    modifier = Modifier
                        .width(6.dp)
                        .weight(0.1f), color = Color.Black
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreen() {
    HomeScreen(navController = rememberNavController())
}