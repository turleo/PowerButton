package space.bm835.pb

import android.app.admin.DeviceAdminReceiver
import android.content.Context
import android.content.Intent

class AdminManager : DeviceAdminReceiver() {

    override fun onDisableRequested(context: Context, intent: Intent): CharSequence {
        return "When disabled, you will no longer able to lock screen with this app."
    }
}