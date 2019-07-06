package space.bm835.pb

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import android.content.Context
import android.content.ComponentName
import android.app.admin.DevicePolicyManager
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.TextView


class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val buildInfoText = findViewById<TextView>(R.id.textView2)

        val manager = this.packageManager
        val info = manager.getPackageInfo(this.packageName, PackageManager.GET_ACTIVITIES)
        buildInfoText.text = info.versionName +'('+ info.versionCode +") master branch"
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
