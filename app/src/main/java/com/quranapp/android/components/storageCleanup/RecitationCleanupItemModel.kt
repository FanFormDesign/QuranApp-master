/*
 * Created by Faisal Khan on (c) 16/8/2021.
 */
package com.anonim.android.components.storageCleanup

import com.anonim.android.api.models.recitation.RecitationInfoModel

data class RecitationCleanupItemModel(
    val recitationModel: RecitationInfoModel,
    val downloadsCount: Int,
    var isCleared: Boolean = false
)
