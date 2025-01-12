package com.anonim.android.api.models.tafsir

import kotlinx.serialization.Serializable

@Serializable
data class AvailableTafsirsModel(
    val tafsirs: Map<String, List<TafsirInfoModel>>
)
