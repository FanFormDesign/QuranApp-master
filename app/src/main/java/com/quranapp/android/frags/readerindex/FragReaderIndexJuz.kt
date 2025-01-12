package com.anonim.android.frags.readerindex

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.anonim.android.adapters.quranIndex.ADPJuzList
import com.anonim.android.views.helper.RecyclerView2

class FragReaderIndexJuz : BaseFragReaderIndex() {
    override fun initList(list: RecyclerView2, ctx: Context) {
        super.initList(list, ctx)
        activity?.runOnUiThread {
            val layoutManager = LinearLayoutManager(ctx)
            list.layoutManager = layoutManager
        }
        resetAdapter(list, ctx, false)
    }

    override fun resetAdapter(list: RecyclerView2, ctx: Context, reverse: Boolean) {
        super.resetAdapter(list, ctx, reverse)
        val adapter = ADPJuzList(this, ctx, reverse)
        activity?.runOnUiThread { list.adapter = adapter }
    }

    companion object {
        @JvmStatic
        fun newInstance(): FragReaderIndexJuz {
            return FragReaderIndexJuz()
        }
    }
}