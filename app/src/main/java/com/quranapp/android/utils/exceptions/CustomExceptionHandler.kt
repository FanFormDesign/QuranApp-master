package com.anonim.android.utils.exceptions

import android.content.Context
import com.anonim.android.utils.Log
import com.anonim.android.utils.app.NotificationUtils
import org.apache.commons.lang3.exception.ExceptionUtils

class CustomExceptionHandler(
    private val ctx: Context
) : Thread.UncaughtExceptionHandler {
    private val defaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()

    override fun uncaughtException(thread: Thread, exc: Throwable) {
        Log.saveCrash(ctx, exc)
        NotificationUtils.showCrashNotification(ctx, ExceptionUtils.getStackTrace(exc))
        defaultExceptionHandler?.uncaughtException(thread, exc)
    }
}
