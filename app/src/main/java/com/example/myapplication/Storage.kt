package com.example.myapplication

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class Storage {

    companion object{
        fun hasStoragePermission(context: Context?): Boolean {
            return (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(
                context!!, Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
                    && PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(
                context, Manifest.permission.READ_EXTERNAL_STORAGE
            ))
        }

        fun requestStoragePermission(
            activity: Activity?,
            requestCode: Int
        ) {
            val permissions = arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            ActivityCompat.requestPermissions(activity!!, permissions, requestCode)
        }

        fun checkPermissionRequest(
            permissions: Array<String>,
            grantResults: IntArray
        ): Boolean {
            if (permissions.size == 0 || grantResults.size == 0) {
                return false
            }
            // If request is cancelled, the result arrays are empty.
            var hasPermission = true
            for (result in grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    hasPermission = false
                    break
                }
            }
            //或许需要提示到底哪个权限没给？就要返回String[]了。  然而，提示个毛，不给还想玩？
            return hasPermission
        }
    }




}