package co.health.test.corona.utils

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat

class PermissionHandler {


    private var permissionReceiver: BroadcastReceiver? = null
    private var listener: OnPermissionResponse? = null
    private var activity: Activity? = null

    fun checkPermission(_activity: Activity, permission: Array<String>, _listener: OnPermissionResponse) {
        listener = _listener
        activity = _activity

        if (Build.VERSION.SDK_INT >= 23) {
            var i = 0
            while (i < permission.size) {
                if (activity!!.checkSelfPermission(permission[i]) == PackageManager.PERMISSION_GRANTED) {
                    if (i == permission.size - 1) {
                        listener!!.onPermissionGranted()
                    }
                } else {
                    i = permission.size
                    registerReceiver()
                    ActivityCompat.requestPermissions(activity!!, permission, 1)

                }
                i++
            }

        } else {
            listener!!.onPermissionGranted()
        }
    }

    /**
     * One Permission
     */
    fun checkPermission(_activity: Activity, permission: String, _listener: OnPermissionResponse) {
        listener = _listener
        activity = _activity

        if (Build.VERSION.SDK_INT >= 23) {
            if (activity!!.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED) {
                listener!!.onPermissionGranted()
            } else {
                registerReceiver()
                ActivityCompat.requestPermissions(activity!!, arrayOf(permission), 1)
            }

        } else {
            listener!!.onPermissionGranted()
        }
    }

    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            listener!!.onPermissionGranted()
        } else {
            listener!!.onPermissionDenied()
        }
    }

    interface OnPermissionResponse {
        fun onPermissionGranted()

        fun onPermissionDenied()
    }

    private fun registerReceiver() {
        permissionReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val extras = intent.extras
                if (extras != null) {
                    val requestCode = extras.getInt("requestCode")
                    val permissions = intent.getStringArrayExtra("permissions")
                    val grantResults = intent.getIntArrayExtra("grantResults")
                    onRequestPermissionsResult(requestCode, permissions, grantResults)
                    activity!!.unregisterReceiver(permissionReceiver)
                }
            }
        }

        val localIntentFilter = IntentFilter()
        localIntentFilter.addAction("PERMISSION_RECEIVER")
        activity!!.registerReceiver(permissionReceiver, localIntentFilter)
    }
}