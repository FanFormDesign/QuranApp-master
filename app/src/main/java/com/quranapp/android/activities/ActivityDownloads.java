/*
 * (c) Faisal Khan. Created on 7/10/2021.
 */

package com.anonim.android.activities;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.anonim.android.R;
import com.anonim.android.activities.base.BaseActivity;
import com.anonim.android.databinding.ActivityDownloadsBinding;
import com.anonim.android.views.BoldHeader;

public class ActivityDownloads extends BaseActivity {
    private ActivityDownloadsBinding mBinding;

    @Override
    protected boolean shouldInflateAsynchronously() {
        return true;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_downloads;
    }

    @Override
    protected void onActivityInflated(@NonNull View activityView, @Nullable Bundle savedInstanceState) {
        mBinding = ActivityDownloadsBinding.bind(activityView);
        initHeader(mBinding.header);
    }

    private void initHeader(BoldHeader header) {
        header.setTitleText("Downloads");
        header.setCallback(this::finish);
        header.setBackgroundColor(color(R.color.colorBGPage));
    }
}
