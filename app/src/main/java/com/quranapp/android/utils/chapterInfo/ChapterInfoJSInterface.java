package com.anonim.android.utils.chapterInfo;

import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.anonim.android.activities.ActivityChapInfo;
import com.anonim.android.components.quran.QuranMeta;
import com.anonim.android.utils.Log;
import com.anonim.android.utils.reader.TranslUtils;
import com.anonim.android.utils.univ.MessageUtils;

import kotlin.Pair;

public class ChapterInfoJSInterface {
    private final ActivityChapInfo mActivity;

    public ChapterInfoJSInterface(ActivityChapInfo activityChapInfo) {
        mActivity = activityChapInfo;
    }

    @JavascriptInterface
    public void openReference(int chapterNo, int fromVerse, int toVerse) {
        QuranMeta quranMeta = mActivity.mQuranMeta;

        if (!QuranMeta.isChapterValid(chapterNo) || !quranMeta.isVerseRangeValid4Chapter(chapterNo, fromVerse,
                toVerse)) {
            Log.d(chapterNo, fromVerse, toVerse);
            MessageUtils.INSTANCE.showRemovableToast(mActivity, "Could not open references", Toast.LENGTH_LONG);
            return;
        }

        mActivity.showReferenceSingleVerseOrRange(
                TranslUtils.defaultTranslationSlugs(),
                chapterNo, new Pair<>(fromVerse, toVerse)
        );
    }
}
