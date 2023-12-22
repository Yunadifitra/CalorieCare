package com.example.caloriecar.ui.screens.add

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.*
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.caloriecar.R
import com.example.caloriecar.di.Injection
import com.example.caloriecar.ui.ViewModelFactory
import java.util.concurrent.Executor
import android.graphics.Bitmap
import android.provider.MediaStore
import androidx.compose.foundation.shape.RoundedCornerShape
import com.example.caloriecar.data.TensorFlowHelper

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "PermissionLaunchedDuringComposition")
@Composable
fun AddScreen(
    modifier: Modifier = Modifier,
    viewModel: AddScreenViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))),
    navigateBack: () -> Unit,
) {
    val context = LocalContext.current
    val defaultImage = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.empty_state_search)

    val isCameraSelected = rememberSaveable { mutableStateOf(false) }
    val bitmap = remember { mutableStateOf(defaultImage) }
    var foodName by rememberSaveable { mutableStateOf("") }
    var confident by rememberSaveable { mutableFloatStateOf(0F) }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri !== null) {
            bitmap.value = MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
        }
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { btm: Bitmap? ->
        btm?.let {
            bitmap.value = it
        }
    }
    val permissionLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                if (isCameraSelected.value) {
                    cameraLauncher.launch()
                } else {
                    galleryLauncher.launch(
                        PickVisualMediaRequest.Builder().setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            .build()
                    )
                }
            } else {
                Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }

    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable { mutableStateOf(false) }

    Surface {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier.fillMaxSize()) {
            TopBar(navigateBack)

            Box(
                modifier = modifier.fillMaxWidth().height(350.dp).padding(10.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer)
            ) {
                bitmap.value?.let { btm ->
                    val resized = Bitmap.createScaledBitmap(
                        btm,
                        TensorFlowHelper.imageSize,
                        TensorFlowHelper.imageSize,
                        false
                    )
                    foodName = TensorFlowHelper.classifyImage(btm, context)
                    Image(
                        bitmap = btm.asImageBitmap(),
                        contentDescription = "Image",
                        modifier = Modifier
                            .fillMaxWidth().fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            IconButton(
                onClick = { isSheetOpen = true }, modifier = modifier
                    .padding(7.dp)
                    .clip(
                        CircleShape
                    )
            ) {
                Icon(imageVector = Icons.Filled.CameraAlt, contentDescription = null)
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(modifier = modifier.padding(8.dp)) {
                Column(
                    modifier = modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Name:", style = MaterialTheme.typography.bodySmall)
                        Text(foodName, style = MaterialTheme.typography.bodySmall)
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Sebagai:", style = MaterialTheme.typography.bodySmall)
                        SelectComponent(
                            listOf(
                                "Sarapan",
                                "Makan Siang",
                                "Makan Malam",
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {},
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(text = "Simpan")
                    }
                }
            }
        }

        if (isSheetOpen) {
            ModalBottomSheet(
                onDismissRequest = {
                    isSheetOpen = false
                },
                sheetState = sheetState,
            ) {
                Box(modifier = modifier.fillMaxWidth()) {
                    Row(
                        modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(30.dp)
                    ) {
                        Column(
                            modifier = modifier.clickable {
                                when (PackageManager.PERMISSION_GRANTED) {
                                    ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) -> {
                                        cameraLauncher.launch()
                                    }

                                    else -> {
                                        isCameraSelected.value = true
                                        permissionLauncher.launch(Manifest.permission.CAMERA)
                                    }
                                }
                            },
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = Icons.Filled.CameraAlt,
                                contentDescription = "camera icon",
                                modifier = modifier
                                    .size(30.dp)
                                    .clip(CircleShape)
                            )
                            Text(
                                "Camera",
                                fontWeight = FontWeight.Normal,
                                modifier = modifier.paddingFromBaseline(top = 16.dp, bottom = 8.dp)
                            )
                        }

                        Column(
                            modifier = modifier.clickable {
                                when (PackageManager.PERMISSION_GRANTED) {
                                    ContextCompat.checkSelfPermission(
                                        context,
                                        Manifest.permission.READ_EXTERNAL_STORAGE
                                    ) -> {
                                        galleryLauncher.launch(
                                            PickVisualMediaRequest.Builder()
                                                .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                                .build()
                                        )
                                    }

                                    else -> {
                                        isCameraSelected.value = false
                                        permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                                    }
                                }
                            },
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Folder,
                                contentDescription = "gallery icon",
                                modifier = modifier
                                    .size(30.dp)
                                    .clip(CircleShape)
                            )
                            Text(
                                "Gallery",
                                fontWeight = FontWeight.Normal,
                                modifier = modifier.paddingFromBaseline(top = 16.dp, bottom = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navigateBack: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = navigateBack) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Menu")
            }
        },
        title = {
            Text(text = "Add")
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectComponent(list: List<String>) {
    var expanded by remember { mutableStateOf(false) }
    var selectedMovie by remember { mutableStateOf(list[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            modifier = Modifier
                .menuAnchor(), // menuAnchor modifier must be passed to the text field for correctness.
            readOnly = true,
            value = selectedMovie,
            onValueChange = {},
            label = { Text("Sebagai") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )

        // menu
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
        ) {
            // menu items
            list.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption) },
                    onClick = {
                        selectedMovie = selectionOption
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun PreviewAddScreen() {
    AddScreen(navigateBack = {})
}


private fun capturePhoto(
    context: Context,
    cameraController: LifecycleCameraController,
    onPhotoCaptured: (Bitmap) -> Unit
) {
    val mainExecutor: Executor = ContextCompat.getMainExecutor(context)

    cameraController.takePicture(mainExecutor, object : ImageCapture.OnImageCapturedCallback() {
        override fun onCaptureSuccess(image: ImageProxy) {
            val correctedBitmap: Bitmap = image
                .toBitmap()
                .rotateBitmap(image.imageInfo.rotationDegrees)

            onPhotoCaptured(correctedBitmap)
            image.close()
        }

        override fun onError(exception: ImageCaptureException) {
            Log.e("CameraContent", "Error capturing image", exception)
        }
    })
}

fun Bitmap.rotateBitmap(rotationDegrees: Int): Bitmap {
    val matrix = Matrix().apply {
        postRotate(-rotationDegrees.toFloat())
        postScale(-1f, -1f)
    }
    return Bitmap.createBitmap(this, 0, 0, width, height, matrix, true)
}