package com.test.auratestapp

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import java.util.*
import java.util.concurrent.TimeUnit

class BootCompletedReceiver: BroadcastReceiver() {


    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context, intent: Intent) {
        scheduleWorkRequest(context)
    }

    private fun scheduleWorkRequest(context: Context) {
        val mWorkManager = WorkManager.getInstance(context)
        val oneTimeRequest = PeriodicWorkRequest.Builder(NotificationWorker::class.java, 15, TimeUnit.MINUTES)
            .addTag(BootCompletedReceiver::class.java.simpleName)
            .build()
        mWorkManager.enqueue(oneTimeRequest)
    }
}