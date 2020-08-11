package com.rootstrap.android.util.permissions

import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.rootstrap.android.ui.base.BaseActivity

open class PermissionActivity : BaseActivity() {

    private var permissionListener: PermissionResponse? = null

    private fun requestPermission(permissions: Array<String>) {
        val notGrantedPermissions = this.checkNoGrantedPermissions(permissions)

        when {
            notGrantedPermissions.isEmpty() -> permissionListener?.granted()
            else -> ActivityCompat.requestPermissions(
                this,
                notGrantedPermissions.toTypedArray(),
                REQUEST_PERMISSION_REQUEST_CODE
            )
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
                        if (!ActivityCompat.shouldShowRequestPermissionRationale(this, s)) {
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
