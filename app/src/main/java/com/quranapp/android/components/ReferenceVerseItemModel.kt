package com.anonim.android.components

import com.anonim.android.components.quran.subcomponents.Verse

class ReferenceVerseItemModel(
    val viewType: Int,
    val verse: Verse?,
    val chapterNo: Int,
    val fromVerse: Int,
    val toVerse: Int,
    val titleText: String?,
    var bookmarked: Boolean,
)
