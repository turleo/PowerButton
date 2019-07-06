package space.bm835.pb

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import android.content.Context
import android.content.ComponentName
import android.app.admin.DevicePolicyManager
import android.content.Intent
import android.net.Uri



class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)


    }

    fun onClickUn(v : View){
        val devicePolicyManager = this.getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
        val componentName = ComponentName(this, AdminManager::class.java)
        devicePolicyManager.removeActiveAdmin(componentName)
        val packageURI = Uri.parse("package:space.bm835.pb")
        val uninstallIntent = Intent(Intent.ACTION_UNINSTALL_PACKAGE, packageURI)
        startActivity(uninstallIntent)
    }

    fun onClickAbout(v : View){
        val packageURI = Uri.parse("package:space.bm835.pb")
        val uninstallIntent = Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI)
        startActivity(uninstallIntent)
    }
}
