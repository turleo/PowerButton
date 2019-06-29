package space.bm835.pb

import android.annotation.TargetApi
import android.app.Activity
import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast

class TurnOffActivity : Activity() {

    //When user click on the power off button we should turn off the screen, so we use this activity to archive it

    //Administrator permission is needed to turn off screen

    internal var devicePolicyManager: DevicePolicyManager? = null
    internal var componentName: ComponentName? = null

    @TargetApi(Build.VERSION_CODES.FROYO)
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Initialize the Administrator component

        devicePolicyManager = getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
        componentName = ComponentName(this, AdminManager::class.java!!)

        //Always check before execute for safety
        if (devicePolicyManager!!.isAdminActive(componentName)) {
            //looks like administrator permission is granted, so just turn off the screen
            devicePolicyManager!!.lockNow()
        } else {
            //Looks like administrator permission is not granted, so show the user current situation
            Toast.makeText(
                this,
                "Device administrator permission is not granted, the application could not perform requested action",
                Toast.LENGTH_LONG
            ).show()

            //button1 clicked, so we request administrator permission
            val intent = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN)
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName)
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, R.string.please_grant)
            startActivityForResult(intent, RESULT_ENABLE)
        }
        //After executing the task we want, this activity is no longer needed, so finish it
        finish()
    }

    companion object {

        //Int value for requesting administrator permission

        internal val RESULT_ENABLE = 1
    }
}