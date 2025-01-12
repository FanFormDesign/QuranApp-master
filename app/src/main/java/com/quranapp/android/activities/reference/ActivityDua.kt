package com.anonim.android.activities.reference

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.anonim.android.R
import com.anonim.android.adapters.reference.ADPDua
import com.anonim.android.components.quran.ExclusiveVerse
import com.anonim.android.components.quran.QuranDua
import com.anonim.android.components.quran.QuranMeta
import com.anonim.android.databinding.ActivityExclusiveVersesBinding

class ActivityDua : ActivityExclusiveVersesBase() {
    override fun onQuranMetaReady(
        activityView: View,
        intent: Intent,
        savedInstanceState: Bundle?,
        quranMeta: QuranMeta
    ) {
        QuranDua.prepareInstance(this, quranMeta) { references ->
            initContent(ActivityExclusiveVersesBinding.bind(activityView), references, R.string.strTitleFeaturedDuas)
        }
    }

    override fun getAdapter(
        context: Context,
        width: Int,
        exclusiveVerses: List<ExclusiveVerse>
    ): RecyclerView.Adapter<*> {
        return ADPDua(context, width, exclusiveVerses)
    }
}