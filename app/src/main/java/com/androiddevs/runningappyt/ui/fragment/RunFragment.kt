package com.androiddevs.runningappyt.ui.fragment

import android.Manifest
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.core.os.persistableBundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.androiddevs.runningappyt.R
import com.androiddevs.runningappyt.other.Constants.REQUEST_PERMISSION_CODE
import com.androiddevs.runningappyt.other.TrackingUtility
import com.androiddevs.runningappyt.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_run.*
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

@AndroidEntryPoint
class RunFragment:Fragment(R.layout.fragment_run) ,EasyPermissions.PermissionCallbacks {
    private val viewModel:MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requestPermission()
        fab.setOnClickListener{

            findNavController().navigate(R.id.action_runFragment_to_trackingFragment)
        }
    }

    private fun requestPermission()
    {
        if (TrackingUtility.hasLocationPermission(requireContext()))
           return
        if (Build.VERSION.SDK_INT<Build.VERSION_CODES.Q)
        {
            EasyPermissions.requestPermissions(
                this,
                "you need to accept location permission to use thi app.",
                REQUEST_PERMISSION_CODE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION

            )
        }
        else
        {
            EasyPermissions.requestPermissions(
                this,
                "you need to accept location permission to use thi app.",
                REQUEST_PERMISSION_CODE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION

            )
        }


    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {

    }

    // Easy permission library shows dialog to user about some permission denied permanently which were needed to run aapp
    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
       if (EasyPermissions.somePermissionPermanentlyDenied(this,perms))
       {
           AppSettingsDialog.Builder(this).build().show()

       }
        else
       {
           requestPermission()
       }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this)
    }
}