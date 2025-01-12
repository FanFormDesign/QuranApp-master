package com.anonim.android.views.homepage2

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import com.peacedesign.android.utils.Dimen
import com.anonim.android.R
import com.anonim.android.activities.reference.ActivityDua
import com.anonim.android.adapters.reference.ADPDua
import com.anonim.android.components.quran.ExclusiveVerse
import com.anonim.android.components.quran.QuranDua
import com.anonim.android.components.quran.QuranMeta
import com.anonim.android.databinding.LytHomepageTitledItemTitleBinding
import com.anonim.android.utils.extensions.color

class FeaturedDuaLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : HomepageCollectionLayoutBase(context, attrs, defStyleAttr) {
    override fun getHeaderTitle(): Int {
        return R.string.strTitleFeaturedDuas
    }

    override fun getHeaderIcon(): Int {
        return R.drawable.dr_icon_rabbana
    }

    override fun setupHeader(header: LytHomepageTitledItemTitleBinding) {
        header.titleIcon.setColorFilter(context.color(R.color.colorPrimary))
    }

    override fun onViewAllClick() {
        context.startActivity(Intent(context, ActivityDua::class.java))
    }

    private fun refreshFeatured(ctx: Context, duas: List<ExclusiveVerse>) {
        hideLoader()

        val featured = duas.subList(0, duas.size.coerceAtMost(6))
        resolveListView().adapter = ADPDua(ctx, Dimen.dp2px(ctx, 200f), featured)
    }

    override  fun refresh(quranMeta: QuranMeta) {
        showLoader()

        QuranDua.prepareInstance(context, quranMeta) { duas ->
            refreshFeatured(context, duas)
        }
    }
}