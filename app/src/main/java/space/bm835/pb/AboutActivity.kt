package space.bm835.pb

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View


class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
    }

    fun onClickUn(v : View){

        startActivity(Intent(Intent.ACTION_UNINSTALL_PACKAGE).apply {
            data = Uri.parse("package:$packageName")
        })
    }

    fun onClickAbout(v : View){
        val packageURI = Uri.parse("package:space.bm835.pb")
        val uninstallIntent = Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI)
        startActivity(uninstallIntent)
    }
}
