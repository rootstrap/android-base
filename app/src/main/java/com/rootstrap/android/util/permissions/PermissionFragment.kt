package com.rootstrap.android.util.permissions

import android.content.pm.PackageManager
import com.rootstrap.android.ui.base.BaseFragment

open class PermissionFragment : BaseFragment() {

    private var permissionListener: PermissionResponse? = null

    private fun requestPermission(permissions: Array<String>) {
        activity?.let { activityContext ->
            val notGrantedPermissions = activityContext.checkNoGrantedPermissions(permissions)

            when {
                notGrantedPermissions.isEmpty() -> permissionListener?.granted()
                else -> requestPermissions(
                    notGrantedPermissions.toTypedArray(),
                    REQUEST_PERMISSION_REQUEST_CODE
                )
            }
        }
    }

    fun checkPermission(permission: String, listener: PermissionResponse) {
        permissionListener = listener
        requestPermission(arrayOf(permission))
    }

    fun checkPermissions(permissions: Array<String>, listener: PermissionResponse) {
        permissionListener = listener
        requestPermission(permissions)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        permissionListener?.let { listener ->
            if (requestCode != REQUEST_PERMISSION_REQUEST_CODE) {
                return
            }

            val permissionDenied = mutableListOf<String>()

            for (i in grantResults.indices) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    permissionDenied.add(permissions[i])
                }
            }

            val granted = permissionDenied.size == 0

            when {
                granted -> listener.granted()
                else -> {
                    for (s in permissionDenied) {
                        if (!shouldShowRequestPermissionRationale(s)) {
                            listener.foreverDenied()
                            return
                        }
                    }
                    listener.denied()
                }
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
