package com.anonim.android.components;

import androidx.annotation.NonNull;

import com.google.common.collect.ImmutableList;
import com.anonim.android.components.quran.QuranMeta;

public class IndexJuzItemModel {
    public String juzTitle;
    public int juzNo;
    public ImmutableList<QuranMeta.ChapterMeta> chapters;

    @NonNull
    @Override
    public String toString() {
        return "juzNo=" + juzNo;
    }
}
