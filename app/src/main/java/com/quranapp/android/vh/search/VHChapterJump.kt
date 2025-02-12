package com.anonim.android.vh.search

import com.anonim.android.components.search.ChapterJumpModel
import com.anonim.android.components.search.SearchResultModelBase
import com.anonim.android.utils.reader.factory.ReaderFactory.startChapter
import com.anonim.android.widgets.chapterCard.ChapterCard

class VHChapterJump(private val chapterCard: ChapterCard, applyMargins: Boolean) : VHSearchResultBase(chapterCard) {
    init {
        setupJumperView(chapterCard, applyMargins)
    }

    override fun bind(model: SearchResultModelBase, pos: Int) {
        if (model is ChapterJumpModel) {
            chapterCard.apply {
                chapterNumber = model.chapterNo
                setName(model.name, model.nameTranslation)
                setOnClickListener { startChapter(it.context, model.chapterNo) }
            }
        }
    }
}