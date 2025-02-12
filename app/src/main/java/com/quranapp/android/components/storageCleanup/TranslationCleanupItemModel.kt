/*
 * Created by Faisal Khan on (c) 16/8/2021.
 */
package com.anonim.android.components.storageCleanup

import com.anonim.android.components.quran.subcomponents.QuranTranslBookInfo
import com.anonim.android.components.transls.TranslBaseModel

class TranslationCleanupItemModel(val bookInfo: QuranTranslBookInfo) : TranslBaseModel() {
    var isDeleted: Boolean = false
}
