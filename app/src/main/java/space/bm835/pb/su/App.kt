package space.bm835.pb.su
 
import android.app.Application
import org.acra.ACRA
import org.acra.ReportingInteractionMode
import org.acra.annotation.ReportsCrashes

@ReportsCrashes(
    mailTo = "bugtracker@bm835.space",
    mode = ReportingInteractionMode.TOAST,
    resToastText = R.string.crash_toast_text
)
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        ACRA.init(this)
    }
}