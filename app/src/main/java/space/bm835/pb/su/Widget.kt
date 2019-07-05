package space.bm835.pb.su

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

class Widget : AppWidgetProvider() {

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
        val turn = powerCode()
        turn.turnOff()

        //It is necessary to call super here
        super.onReceive(context, intent)
    }

    companion object {

        //Intent string, dont forget to register it in manifest
        val ACTION_WIDGET_CLICKED = "ActionWidgetClicked"
    }
}