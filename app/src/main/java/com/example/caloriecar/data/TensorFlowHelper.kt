package com.example.caloriecar.data

import android.content.Context
import android.graphics.Bitmap
import com.example.caloriecar.ml.Food
import com.example.caloriecar.ml.Model
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.common.ops.NormalizeOp
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import org.tensorflow.lite.support.label.Category
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer
import java.nio.ByteOrder

object TensorFlowHelper {
    const val imageSize = 224
    fun classifyImage(bitmap: Bitmap, context: Context): String {
        val imageProcessor = ImageProcessor.Builder()
            .add(ResizeOp(224, 224, ResizeOp.ResizeMethod.BILINEAR))
            .build()

        val model = Model.newInstance(context)

        // Creates inputs for reference.
        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.FLOAT32)

        var tensorImage = TensorImage(DataType.FLOAT32)
        tensorImage.load(bitmap)
        tensorImage = imageProcessor.process(tensorImage)

        inputFeature0.loadBuffer(tensorImage.buffer)

        // Runs model inference and gets result.
        val outputs = model.process(inputFeature0)
        val outputFeature0 = outputs.outputFeature0AsTensorBuffer

        val confidences = outputFeature0.floatArray
        val maxIndex = getMax(confidences)

//        for (i in confidences.indices) {
//            if (confidences[i] > maxConfidence) {
//                maxConfidence = confidences[i]
//                maxIndex = i
//            }
//        }

        val labels = arrayOf(
            "bakso", "cheesecake", "chicken_wings", "churros", "donuts",
            "egg", "french_fries", "fried_rice", "gado", "hamburger",
            "hot_dog", "mac_and_cheese", "pancakes", "pizza", "rendang",
            "sate", "soup", "spaghetti", "sushi", "takoyaki", "waffles"
        )

        // Releases model resources if no longer used.
        model.close()

        return labels[maxIndex]
    }

    private fun getMax(arr: FloatArray): Int {
        var maxIndex = 0
        arr.forEachIndexed { index, fl ->
            if (arr[maxIndex] < fl) {
                maxIndex = index
            }
        }
        return maxIndex
    }
}