package com.soft.facemesh

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.Size
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.common.Triangle
import com.google.mlkit.vision.facemesh.FaceMeshDetection
import com.google.mlkit.vision.facemesh.FaceMeshDetectorOptions
import com.google.mlkit.vision.facemesh.FaceMeshPoint
import com.soft.base.BaseFragment
import com.soft.facemesh.databinding.FragmentFaceMeshBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@ExperimentalGetImage
class FaceMeshFragment : BaseFragment<FragmentFaceMeshBinding, FaceMeshViewModel>(R.layout.fragment_face_mesh) {

    override val layoutResourceId: Int
        get() = R.layout.fragment_face_mesh

    override val viewModel: FaceMeshViewModel by viewModels()

    private var imageCapture: ImageCapture? = null
    private val defaultDetector = FaceMeshDetection.getClient()
    private val boundingBoxDetector = FaceMeshDetection.getClient(FaceMeshDetectorOptions.Builder().build())
    private lateinit var cameraExecutor: ExecutorService


    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    override fun initStartView() {
        binding.apply {
            this.vm = viewModel
            this.lifecycleOwner = viewLifecycleOwner
        }
        exception = viewModel.errorEvent
        checkPermuissions()
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }

    private fun checkPermuissions() {
        // Request camera permissions
        if (allPermissionsGranted()) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(), REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS
            )
        }
        cameraExecutor = Executors.newSingleThreadExecutor()
    }
    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener({
            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.previewView.surfaceProvider)
                }

            val imageAnalysis = ImageAnalysis.Builder()
                .setTargetResolution(Size(1280, 720))
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()

            imageAnalysis.setAnalyzer(cameraExecutor, ImageAnalysis.Analyzer { imageProxy ->
                val mediaImage = imageProxy.image
                if (mediaImage != null) {
                    val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

                    boundingBoxDetector.process(image)
                        .addOnSuccessListener { faceMeshs ->
                            for (faceMesh in faceMeshs) {
                                val bounds: Rect = faceMesh.boundingBox

                                val faceMeshpoints = faceMesh.allPoints
                                faceMeshpoints.forEachIndexed { index, faceMeshPoint ->
                                    val index: Int = index
                                    val position = faceMeshPoint.position
                                }

                                val triangles: List<Triangle<FaceMeshPoint>> = faceMesh.allTriangles
                                for (triangle in triangles) {
                                    val connectedPoints = triangle.allPoints
                                }
                            }
                        }
                        .addOnFailureListener { e ->
                            Log.e(TAG, "startCamera: ${e.message}")
                        }
                }

                imageProxy.close()
            })


            // Select back camera as a default
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(this, cameraSelector, preview)
                cameraProvider.bindToLifecycle(this, cameraSelector,imageAnalysis,preview)

            } catch(exc: Exception) {
                Log.e(TAG, "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        private const val TAG = "CameraXApp"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS =
            mutableListOf (
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO
            ).apply {
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                    add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                }
            }.toTypedArray()
    }
}


