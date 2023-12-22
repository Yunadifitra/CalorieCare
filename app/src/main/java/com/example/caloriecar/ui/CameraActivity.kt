package com.example.caloriecar.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.core.content.ContextCompat
import com.example.caloriecar.R
import com.example.caloriecar.data.getImageUri
import com.example.caloriecar.ml.Food
import com.example.caloriecar.ml.Model
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.common.ops.NormalizeOp
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer

class CameraActivity : AppCompatActivity() {

    private lateinit var bitmap: Bitmap
    private lateinit var imgView: ImageView
    private lateinit var btnPrediction: Button
    private lateinit var btnCamera: Button
    private lateinit var btnGallery: Button
    private lateinit var tvName: TextView
    private var currentImageUri: Uri? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        if (!allPermissionsGranted()) {
            requestPermissionLauncher.launch(REQUIRED_PERMISSION)
        }

        val imageProcessor = ImageProcessor.Builder()
            .add(NormalizeOp(0.0f, 255.0f))
            .add(ResizeOp(224, 224, ResizeOp.ResizeMethod.BILINEAR))
            .build()

        val labels = application.assets.open("labels.txt").bufferedReader().readLines()

        imgView = findViewById(R.id.previewImageView)
        btnCamera = findViewById(R.id.cameraButton)
        btnGallery = findViewById(R.id.galleryButton)
        btnPrediction = findViewById(R.id.predictionButton)
        tvName = findViewById(R.id.descriptionText)

        btnGallery.setOnClickListener { startGallery() }
        btnCamera.setOnClickListener { startCamera() }
        btnPrediction.setOnClickListener {
            val model = Model.newInstance(this@CameraActivity)

            var tensorImage = TensorImage(DataType.FLOAT32)
            tensorImage.load(bitmap)

            tensorImage = imageProcessor.process(tensorImage)

            // Creates inputs for reference.
            val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.FLOAT32)
            inputFeature0.loadBuffer(tensorImage.buffer)

            // Runs model inference and gets result.
            val outputs = model.process(inputFeature0)
            val outputFeature0 = outputs.outputFeature0AsTensorBuffer

            var maxIndex = 0
            outputFeature0.floatArray.forEachIndexed { index, fl ->
                if (outputFeature0.floatArray[maxIndex] < fl) {
                    maxIndex = index
                }
            }

            // Releases model resources if no longer used.
            model.close()

            tvName.text = labels[maxIndex]
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(this, "Permission request granted", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Permission request denied", Toast.LENGTH_LONG).show()
            }
        }

    private fun allPermissionsGranted() =
        ContextCompat.checkSelfPermission(
            this,
            REQUIRED_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED

    private fun startGallery() {
        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            currentImageUri = uri // for now currentImageUri is not used because we need `Bitmap` format not `URI`
            bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
            imgView.setImageBitmap(bitmap)
        } else {
            Log.d("Photo Picker", "No media selected")
        }
    }

    private fun startCamera() {
        currentImageUri = getImageUri(this)
        launcherIntentCamera.launch()
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.TakePicturePreview()
    ) { btm: Bitmap? ->
        btm?.let {
            bitmap = btm
            imgView.setImageBitmap(btm)
        }
    }

    private fun showImage() {
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            imgView.setImageURI(it)
        }
    }

    companion object {
        private const val REQUIRED_PERMISSION = Manifest.permission.CAMERA
    }
}