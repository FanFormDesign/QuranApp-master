package com.anonim.android.vh.search

import android.content.Context
import android.graphics.Typeface
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextUtils
import android.text.style.TextAppearanceSpan
import androidx.core.content.ContextCompat
import com.peacedesign.android.utils.Dimen
import com.anonim.android.R
import com.anonim.android.components.search.SearchResultModelBase
import com.anonim.android.components.search.TafsirJumpModel
import com.anonim.android.utils.extensions.colorStateList
import com.anonim.android.utils.extensions.drawable
import com.anonim.android.utils.extensions.getDimenPx
import com.anonim.android.utils.extensions.updatePaddings
import com.anonim.android.utils.reader.factory.ReaderFactory.startTafsir
import com.anonim.android.widgets.IconedTextView

class VHTafsirJump(private val mTextView: IconedTextView, applyMargins: Boolean) : VHSearchResultBase(mTextView) {
    init {
        mTextView.updatePaddings(Dimen.dp2px(mTextView.context, 15f), Dimen.dp2px(mTextView.context, 10f))
        setupJumperView(mTextView, applyMargins)
    }

    override fun bind(model: SearchResultModelBase, pos: Int) {
        if(model is TafsirJumpModel){
            mTextView.apply {
                setDrawables(mTextView.context.drawable(R.drawable.dr_icon_tafsir), null, null, null)
                text = makeName(itemView.context, model.titleText, model.chapterNameText)
                setOnClickListener { startTafsir(it.context, model.chapterNo, model.verseNo) }
            }
        }
    }

    private fun makeName(ctx: Context, text: String, subtext: String): CharSequence {
        val ssb = SpannableStringBuilder()

        val SANS_SERIF = "sans-serif"
        val nameClr = ContextCompat.getColorStateList(ctx, R.color.color_reader_spinner_item_label)

        val nameSS = SpannableString(text)
        setSpan(nameSS, TextAppearanceSpan(SANS_SERIF, Typeface.BOLD, -1, nameClr, null))
        ssb.append(nameSS)

        if (!TextUtils.isEmpty(subtext)) {
            val translClr = ctx.colorStateList(R.color.color_reader_spinner_item_label2)
            val dimen2 = ctx.getDimenPx(R.dimen.dmnCommonSize2)

            val translSS = SpannableString(subtext)
            setSpan(translSS, TextAppearanceSpan(SANS_SERIF, Typeface.NORMAL, dimen2, translClr, null))
            ssb.append("\n").append(translSS)
        }
        return ssb
    }

    private fun setSpan(ss: SpannableString, obj: Any) {
        ss.setSpan(obj, 0, ss.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    }
}