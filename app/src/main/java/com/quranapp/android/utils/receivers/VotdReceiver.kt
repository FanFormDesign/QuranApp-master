package com.anonim.android.utils.receivers

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.text.TextUtils
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.anonim.android.R
import com.anonim.android.activities.ActivityReader
import com.anonim.android.components.quran.QuranMeta
import com.anonim.android.interfaceUtils.OnResultReadyCallback
import com.anonim.android.utils.app.NotificationUtils
import com.anonim.android.utils.reader.TranslUtils
import com.anonim.android.utils.reader.factory.QuranTranslationFactory
import com.anonim.android.utils.reader.factory.ReaderFactory.prepareSingleVerseIntent
import com.anonim.android.utils.sharedPrefs.SPReader
import com.anonim.android.utils.sharedPrefs.SPVerses
import com.anonim.android.utils.univ.Codes
import com.anonim.android.utils.univ.Keys
import com.anonim.android.utils.univ.StringUtils
import com.anonim.android.utils.verse.VerseUtils
import com.anonim.android.utils.votd.VOTDUtils

class VotdReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        if (!SPVerses.getVOTDReminderEnabled(context)) {
            return
        }

        QuranMeta.prepareInstance(context, object : OnResultReadyCallback<QuranMeta> {
            override fun onReady(r: QuranMeta) {
                VerseUtils.getVOTD(context, r, null) { chapterNo, verseNo ->
                    if (!QuranMeta.isChapterValid(chapterNo) || !r.isVerseValid4Chapter(chapterNo, verseNo)) {
                        return@getVOTD
                    }

                    var slugs = SPReader.getSavedTranslations(context)
                    if (slugs.isEmpty()) {
                        slugs = TranslUtils.defaultTranslationSlugs()
                    }

                    QuranTranslationFactory(context).use {
                        val verseTranslation = it.getTranslationsSingleVerse(slugs, chapterNo, verseNo)

                        if (verseTranslation.isEmpty()) {
                            return@use
                        }

                        val notificationId = Codes.NOTIF_ID_VOTD

                        var flag = PendingIntent.FLAG_CANCEL_CURRENT
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            flag = flag or PendingIntent.FLAG_IMMUTABLE
                        }

                        val readerIntent = prepareSingleVerseIntent(chapterNo, verseNo)
                        readerIntent.putExtra(Keys.READER_KEY_TRANSL_SLUGS, slugs.toTypedArray())
                        readerIntent.setClass(context, ActivityReader::class.java)
                        val readerPendingIntent = PendingIntent.getActivity(context, notificationId, readerIntent, flag)

                        val channelId = NotificationUtils.CHANNEL_ID_VOTD
                        val builder = NotificationCompat.Builder(context, channelId)
                        builder.setAutoCancel(true)
                        builder.setCategory(NotificationCompat.CATEGORY_REMINDER)
                        builder.setContentIntent(readerPendingIntent)

                        builder.setSmallIcon(
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) R.drawable.dr_ic_shortcut_votd
                            else R.drawable.dr_logo
                        )

                        val msg = context.getString(
                            R.string.strLabelVerseSerialWithChapter,
                            r.getChapterName(context, chapterNo),
                            chapterNo,
                            verseNo
                        )

                        builder.setContentTitle(TextUtils.concat(context.getString(R.string.strTitleVOTD), " - ", msg))

                        val translationText = StringUtils.removeHTML(verseTranslation[0].text, false)

                        builder.setContentText(translationText)
                        builder.setStyle(NotificationCompat.BigTextStyle().bigText(translationText))

                        (ContextCompat.getSystemService(context, NotificationManager::class.java))?.notify(
                            notificationId,
                            builder.build()
                        )
                    }
                }
            }
        })

        if (VOTDUtils.isVOTDTrulyEnabled(context)) {
            VOTDUtils.enableVOTDReminder(context)
        }
    }
}