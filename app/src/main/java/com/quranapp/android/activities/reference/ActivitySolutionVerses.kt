package com.anonim.android.activities.reference

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.anonim.android.R
import com.anonim.android.adapters.reference.ADPSolutionVerses
import com.anonim.android.components.quran.ExclusiveVerse
import com.anonim.android.components.quran.QuranMeta
import com.anonim.android.components.quran.SituationVerse
import com.anonim.android.databinding.ActivityExclusiveVersesBinding

class ActivitySolutionVerses : ActivityExclusiveVersesBase() {
    override fun onQuranMetaReady(
        activityView: View,
        intent: Intent,
        savedInstanceState: Bundle?,
        quranMeta: QuranMeta
    ) {
        SituationVerse.prepareInstance(this, quranMeta) { references ->
            initContent(ActivityExclusiveVersesBinding.bind(activityView), references, R.string.titleSolutionVerses)
        }
    }

    override fun getAdapter(
        context: Context,
        width: Int,
        exclusiveVerses: List<ExclusiveVerse>
    ): RecyclerView.Adapter<*> {
        return ADPSolutionVerses(context, width, exclusiveVerses)
    }
}