package space.bm835.pb

import android.annotation.TargetApi
import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.service.quicksettings.TileService
import android.widget.Toast

@TargetApi(Build.VERSION_CODES.N)
class ButtonTileService : TileService() {

    private var devicePolicyManager: DevicePolicyManager? = null
    private var componentName: ComponentName? = null

    override fun onClick() {
        super.onClick()

        devicePolicyManager = getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
        componentName = ComponentName(this, AdminManager::class.java)

        //Always check before execute for safety
        if (devicePolicyManager!!.isAdminActive(componentName)) {
            //looks like administrator permission is granted, so just turn off the screen
            devicePolicyManager!!.lockNow()
        } else {
            //Looks like administrator permission is not granted, so show the user current situation
            Toast.makeText(
                this,
                R.string.sorry_open_activity_first,
                Toast.LENGTH_LONG
            ).show()
        }
        // Called when the user click the tile
    }

    override fun onTileRemoved() {
        super.onTileRemoved()

        // Do something when the user removes the Tile
    }

    override fun onTileAdded() {
        super.onTileAdded()

        // Do something when the user add the Tile
    }

    override fun onStartListening() {
        super.onStartListening()

        // Called when the Tile becomes visible
    }

    override fun onStopListening() {
        super.onStopListening()

        // Called when the tile is no longer visible
    }

    companion object {

        internal val RESULT_ENABLE = 1
    }
}