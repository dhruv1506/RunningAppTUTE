package com.androiddevs.runningappyt.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_LOW
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import com.androiddevs.runningappyt.R
import com.androiddevs.runningappyt.other.Constants.ACTION_PAUSE_SERVICE
import com.androiddevs.runningappyt.other.Constants.ACTION_SHOW_TRACKING_FRAGMENT
import com.androiddevs.runningappyt.other.Constants.ACTION_START_OR_RESUME_SERVICE
import com.androiddevs.runningappyt.other.Constants.ACTION_STOP_SERVICE
import com.androiddevs.runningappyt.other.Constants.NOTIFICATION_CHANNEL_ID
import com.androiddevs.runningappyt.other.Constants.NOTIFICATION_CHANNEL_NAME
import com.androiddevs.runningappyt.other.Constants.NOTIFICATION_ID
import com.androiddevs.runningappyt.ui.MainActivity
import timber.log.Timber

class TrackingService : LifecycleService(){

    var isFirstRun=true
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        intent?.let {

            when(it.action)
            {
                ACTION_START_OR_RESUME_SERVICE-> {
                    //Timber.d("service started or resume")

                    if (isFirstRun)
                    {
                       startForegroundService()
                       isFirstRun=false
                    }
                    else
                    {
                        Timber.d("Resuming service...")
                    }
                }
                ACTION_STOP_SERVICE-> {
                    Timber.d("service stop")
                }
                ACTION_PAUSE_SERVICE-> {
                    Timber.d("service pause")
                }


            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun startForegroundService()
    {
        val notificationManager= getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            createNotificationChannel(notificationManager)
        }

        val notificationBuilder=NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setAutoCancel(false)
            .setOngoing(false)
            .setSmallIcon(R.drawable.ic_directions_run_black_24dp)
            .setContentTitle("RunningApp")
            .setContentText("00:00:00")
            .setContentIntent(getMainActivityPendingIntent())

        startForeground(NOTIFICATION_ID,notificationBuilder.build())
    }

    // we passes action to open tracker fragment once user click on notification
    private fun getMainActivityPendingIntent()=PendingIntent.getActivity(
        this,
        0,
        Intent(this,MainActivity::class.java).also {
            it.action=ACTION_SHOW_TRACKING_FRAGMENT
        },
        FLAG_UPDATE_CURRENT// it updates existing instance main activity instead create new instance
    )
    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(notificationManager:NotificationManager)
    {
        val  channel=NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                NOTIFICATION_CHANNEL_NAME,
                IMPORTANCE_LOW)
        notificationManager.createNotificationChannel(channel)

    }
}