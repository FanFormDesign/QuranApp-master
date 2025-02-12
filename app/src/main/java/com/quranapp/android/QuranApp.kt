package com.anonim.android

import android.app.Application
import android.content.Context
import android.os.Build
import android.webkit.WebView
import androidx.appcompat.app.AppCompatDelegate
import com.anonim.android.api.RetrofitInstance
import com.anonim.android.utils.app.DownloadSourceUtils
import com.anonim.android.utils.app.NotificationUtils
import com.anonim.android.utils.app.ThemeUtils
import com.anonim.android.utils.exceptions.CustomExceptionHandler
import com.anonim.android.utils.univ.FileUtils

class QuranApp : Application() {
    override fun attachBaseContext(base: Context) {
        initBeforeBaseAttach(base)
        super.attachBaseContext(base)
    }

    private fun initBeforeBaseAttach(base: Context) {
        FileUtils.appFilesDir = base.filesDir
        updateTheme(base)
    }

    private fun updateTheme(base: Context) {
        AppCompatDelegate.setDefaultNightMode(ThemeUtils.resolveThemeModeFromSP(base))
    }

    override fun onCreate() {
        super.onCreate()
        DownloadSourceUtils.resetDownloadSourceBaseUrl(this)
        NotificationUtils.createNotificationChannels(this)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val process = getProcessName()
            if (packageName != process) WebView.setDataDirectorySuffix(process)
        }

        // Handler for uncaught exceptions
        Thread.setDefaultUncaughtExceptionHandler(CustomExceptionHandler(this))
    }
}
