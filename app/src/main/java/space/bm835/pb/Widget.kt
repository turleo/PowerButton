package space.bm835.pb

import android.app.PendingIntent
import android.app.admin.DevicePolicyManager
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.Toast

class Widget : AppWidgetProvider() {

    //Administrator component
    internal var devicePolicyManager: DevicePolicyManager? = null
    internal var componentName: ComponentName? = null

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {

        val N = appWidgetIds.size

        for (i in 0 until N) {

            //The int value
            val appWidgetId = appWidgetIds[i]

            //Setup the removeView
            val remoteViews = RemoteViews(context.packageName, R.layout.widget)

            //Setup intent
            val mIntent = Intent(context, Widget::class.java)

            //Set the action
            mIntent.action = ACTION_WIDGET_CLICKED

            //Setup PendingIntent
            val pendingIntent = PendingIntent.getBroadcast(context, 0, mIntent, 0)

            //Set the on click listener to the button
            remoteViews.setOnClickPendingIntent(R.id.imageView, pendingIntent)

            //Update the widget
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {

        //Check if the intent we received is the widget clicked
        if (intent.action == ACTION_WIDGET_CLICKED) {
            //Initialize the administrator component here because we need to use it here only
            devicePolicyManager = context.getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
            componentName = ComponentName(context, AdminManager::class.java)

            //Check if administrator permission granted first before executing, because we don't know if user would disable it in the settings
            if (devicePolicyManager!!.isAdminActive(componentName)) {
                //Looks like user has granted administrator permission, we just need to lock the screen
                devicePolicyManager!!.lockNow()
            } else {
                //Looks like administrator permission is not granted, so show the user current situation
                Toast.makeText(
                    context,
                    "Device administrator permission is not granted, the application could not perform requested action",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        //It is necessary to call super here
        super.onReceive(context, intent)
    }

    companion object {

        //Intent string, dont forget to register it in manifest
        val ACTION_WIDGET_CLICKED = "ActionWidgetClicked"
    }
}