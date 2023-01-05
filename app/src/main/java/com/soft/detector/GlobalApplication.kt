package com.soft.detector

import android.app.Application
import com.google.mlkit.vision.facemesh.FaceMeshDetection
import com.google.mlkit.vision.facemesh.FaceMeshDetectorOptions

class GlobalApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val defaultDetector = FaceMeshDetection.getClient()

        val boundingBoxDetector = FaceMeshDetection.getClient(FaceMeshDetectorOptions.Builder().build())
    }
}