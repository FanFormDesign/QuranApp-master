package com.anonim.android.vh.search

import com.anonim.android.components.search.JuzJumpModel
import com.anonim.android.components.search.SearchResultModelBase
import com.anonim.android.databinding.LytReaderJuzSpinnerItemBinding
import com.anonim.android.utils.reader.factory.ReaderFactory.startJuz

class VHJuzJump(private val mBinding: LytReaderJuzSpinnerItemBinding, applyMargins: Boolean) : VHSearchResultBase(
    mBinding.root
) {
    init {
        setupJumperView(mBinding.root, applyMargins)
    }

    override fun bind(model: SearchResultModelBase, pos: Int) {
        (model as JuzJumpModel).apply {
            mBinding.juzSerial.text = model.juzSerial
            mBinding.root.setOnClickListener { startJuz(it.context, model.juzNo) }
        }
    }
}
