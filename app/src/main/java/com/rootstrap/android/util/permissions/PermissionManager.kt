package com.rootstrap.android.util.permissions

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.core.content.ContextCompat.checkSelfPermission

interface PermissionResponse {
    fun granted()
    fun denied()
    fun foreverDenied()
}

val REQUEST_PERMISSION_REQUEST_CODE = 999

fun Context.checkPermission(permission: String): Boolean =
    Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checkSelfPermission(
        this,
        permission
    ) == PackageManager.PERMISSION_GRANTED

fun Context.checkNotGrantedPermissions(permissions: Array<String>): List<String> =
    permissions.filter { !checkPermission(it) }

/**
 * Use this extension to open the app details to grant permission manually
 * in case that the user denied the permission all the time
 * **/
fun Context.openAppSettings() =
    startActivity(
        Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).also {
            it.data = Uri.parse("package:" + this.packageName)
        }
    )
