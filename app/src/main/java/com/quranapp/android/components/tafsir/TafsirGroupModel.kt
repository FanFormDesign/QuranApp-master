/*
 * Created by Faisal Khan on (c) 16/8/2021.
 */
package com.anonim.android.components.tafsir

import com.anonim.android.api.models.tafsir.TafsirInfoModel

class TafsirGroupModel(
    val langCode: String,
) {
    var langName = ""
    var tafsirs: List<TafsirInfoModel> = ArrayList()
    var isExpanded = false
}
