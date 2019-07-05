package space.bm835.pb.su

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import java.io.DataOutputStream
import java.io.IOException


class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val buildInfoText = findViewById<TextView>(R.id.textView2)

        val manager = this.packageManager
        val info = manager.getPackageInfo(this.packageName, PackageManager.GET_ACTIVITIES)
        buildInfoText.text = info.versionName +'('+ info.versionCode +") SU branch"
    }

    fun onClickUn(v : View){

        //execute input power button
        try {
            val su = Runtime.getRuntime().exec("su")
            val outputStream = DataOutputStream(su.outputStream)

            outputStream.writeBytes("pm uninstall space.bm835.pb.su\n")
            outputStream.flush()

            outputStream.writeBytes("exit\n")
            outputStream.flush()
            finish()
            su.waitFor()
        } catch (e: IOException) {
            throw Exception(e)
        } catch (e: InterruptedException) {
            throw Exception(e)
        }
    }

    fun onClickAbout(v : View){
        val packageURI = Uri.parse("package:space.bm835.pb.su")
        val uninstallIntent = Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI)
        startActivity(uninstallIntent)
    }
}
