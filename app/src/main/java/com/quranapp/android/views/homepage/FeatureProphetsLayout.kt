package com.anonim.android.views.homepage2

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import com.anonim.android.R
import com.anonim.android.activities.reference.ActivityProphets
import com.anonim.android.adapters.ADPProphets
import com.anonim.android.components.quran.QuranMeta
import com.anonim.android.components.quran.QuranProphet.Companion.prepareInstance
import com.anonim.android.utils.extensions.dp2px

class FeatureProphetsLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : HomepageCollectionLayoutBase(context, attrs, defStyleAttr) {
    override fun getHeaderTitle(): Int {
        return R.string.strTitleFeaturedProphets;
    }

    override fun getHeaderIcon(): Int {
        return R.drawable.prophets;
    }

    override fun onViewAllClick() {
        context.startActivity(Intent(context, ActivityProphets::class.java))
    }

    override fun refresh(quranMeta: QuranMeta) {
        showLoader()
        prepareInstance(context, quranMeta) { quranProphet ->
            hideLoader()
            resolveListView().adapter = ADPProphets(context, context.dp2px(300f), 10).apply {
                prophets = quranProphet.prophets
            }
        }
    }
}