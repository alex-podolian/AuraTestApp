package com.test.auratestapp

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.test.auratestapp.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        scheduleWorkRequest(this)
    }

    private fun scheduleWorkRequest(context: Context) {
        val mWorkManager = WorkManager.getInstance(this)
        val request = PeriodicWorkRequest.Builder(NotificationWorker::class.java, 15, TimeUnit.MINUTES)
            .addTag(MainActivity::class.java.simpleName)
            .build()
        mWorkManager.enqueue(request)

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.id)
            .observe(context as AppCompatActivity
            ) {
                binding.counterText.text = it.outputData.getString("some_tag")
            }
    }
}
