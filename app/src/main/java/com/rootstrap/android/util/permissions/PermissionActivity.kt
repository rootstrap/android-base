package com.rootstrap.android.util.permissions

import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.rootstrap.android.ui.base.BaseActivity

open class PermissionActivity : BaseActivity() {

    private var permissionListener: PermissionResponse? = null

    fun requestPermission(permissions: Array<String>, listener: PermissionResponse) {
        permissionListener = listener
        val notGrantedPermissions = this.checkNotGrantedPermissions(permissions)

        when {
            notGrantedPermissions.isEmpty() -> permissionListener?.granted()
            else -> ActivityCompat.requestPermissions(
                this,
                notGrantedPermissions.toTypedArray(),
                REQUEST_PERMISSION_REQUEST_CODE
            )
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
                        if (!ActivityCompat.shouldShowRequestPermissionRationale(this, deniedPermission)) {
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
