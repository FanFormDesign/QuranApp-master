package com.anonim.android.components.search

import com.anonim.android.components.quran.subcomponents.QuranTranslBookInfo

class VerseResultCountModel(val bookInfo: QuranTranslBookInfo?) : SearchResultModelBase() {
    @JvmField
    var resultCount = 0
}
