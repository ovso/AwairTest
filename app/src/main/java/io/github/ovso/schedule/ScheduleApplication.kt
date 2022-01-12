@file:Suppress("SpellCheckingInspection")

package io.github.ovso.schedule

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger


@HiltAndroidApp
class ScheduleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Logger.addLogAdapter(AndroidLogAdapter())
    }
}