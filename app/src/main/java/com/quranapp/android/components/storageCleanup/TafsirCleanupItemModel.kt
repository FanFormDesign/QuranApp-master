/*
 * Created by Faisal Khan on (c) 16/8/2021.
 */
package com.anonim.android.components.storageCleanup

import com.anonim.android.api.models.tafsir.TafsirInfoModel

data class TafsirCleanupItemModel(
    val tafsirModel: TafsirInfoModel,
    val downloadsCount: Int,
    var isCleared: Boolean = false
)
