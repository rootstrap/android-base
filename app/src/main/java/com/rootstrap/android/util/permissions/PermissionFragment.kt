package com.rootstrap.android.util.permissions

import android.content.pm.PackageManager
import com.rootstrap.android.ui.base.BaseFragment

open class PermissionFragment : BaseFragment() {

    private var permissionListener: PermissionResponse? = null

    private fun requestPermission(permissions: Array<String>, listener: PermissionResponse) {
        permissionListener = listener
        activity?.let { activityContext ->
            val notGrantedPermissions = activityContext.checkNotGrantedPermissions(permissions)

            when {
                notGrantedPermissions.isEmpty() -> permissionListener?.granted()
                else -> requestPermissions(
                    notGrantedPermissions.toTypedArray(),
                    REQUEST_PERMISSION_REQUEST_CODE
                )
            }
        }
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

            val deniedPermissions = mutableListOf<String>()

            for (i in grantResults.indices) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    deniedPermissions.add(permissions[i])
                }
            }

            when {
                deniedPermissions.isEmpty() -> listener.granted()
                else -> {
                    for (deniedPermission in deniedPermissions) {
                        if (!shouldShowRequestPermissionRationale(deniedPermission)) {
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
