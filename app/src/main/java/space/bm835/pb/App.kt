package space.bm835.pb
 
import android.app.Application
import android.content.Context
import org.acra.ACRA
import org.acra.ReportingInteractionMode
import org.acra.annotation.ReportsCrashes
 
@ReportsCrashes(
    mailTo = "bugtracker@bm835.space",
    mode = ReportingInteractionMode.TOAST,
    resToastText = R.string.crash_toast_text
)
class App: Application(){
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        ACRA.init(this)
    }
}