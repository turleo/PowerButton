package space.bm835.pb
 
import android.app.Application
import org.acra.ACRA
import org.acra.ReportingInteractionMode
import org.acra.annotation.ReportsCrashes

@ReportsCrashes(
    mailTo = "leonid@national.shitposting.agency",
    mode = ReportingInteractionMode.TOAST,
    resToastText = R.string.crash_toast_text
)
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        ACRA.init(this)
    }
}