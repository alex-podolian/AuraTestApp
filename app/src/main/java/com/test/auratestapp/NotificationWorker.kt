package com.test.auratestapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

class NotificationWorker(private var context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {
        displayNotification()
        return Result.success()
    }

    private fun displayNotification() {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "some_id",
                "some_name",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
        val text = String.format(context.getString(R.string.boot_detected_text), System.currentTimeMillis())
        val id = com.test.auratestapp.getId()
        val notification = NotificationCompat.Builder(applicationContext, "simplifiedcoding")
            .setContentTitle("")
            .setContentText(text)
            .setSmallIcon(R.mipmap.ic_launcher)
        notificationManager.notify(id, notification.build())
    }
}