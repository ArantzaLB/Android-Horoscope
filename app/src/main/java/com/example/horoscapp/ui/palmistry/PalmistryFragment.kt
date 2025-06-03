package com.example.horoscapp.ui.palmistry

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import androidx.fragment.app.Fragment
import com.example.horoscapp.databinding.FragmentPalmistryBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PalmistryFragment : Fragment() {

    companion object {
        private const val CAMERA_PERMISSION = android.Manifest.permission.CAMERA
    }

    private var _binding: FragmentPalmistryBinding? = null
    private val binding get() = _binding!!

    //Ésto sirve para pedir los permisos
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            //startCamera, se pone ésto porque la primera vez que lo acepte debe lanzar la camera
            startCamera()
        } else {
            Toast.makeText(
                requireContext(),
                "Acepta los permisos para una mágica experiencia",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPalmistryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    //Cuando la vista ya está creada
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (checkCameraPermission()) {
            //Tiene el permiso aceptado
            //startCamera
            startCamera()
        } else {
            //Pide permiso
            requestPermissionLauncher.launch(CAMERA_PERMISSION)
        }
    }

    //Inicia la camara
    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
            }

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this, cameraSelector, preview)
            } catch (e: Exception) {
                Log.e("arantza", "Algo salio mal ${e.message}")
            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    //Checa los permisos de la camara
    private fun checkCameraPermission(): Boolean {
        //Se encarga de checar si el permiso ya fue dado
        return PermissionChecker.checkCallingOrSelfPermission(
            requireContext(), CAMERA_PERMISSION
        ) == PermissionChecker.PERMISSION_GRANTED

    }

}