package com.anonim.android.activities

import android.os.Bundle
import android.view.View
import com.anonim.android.R
import com.anonim.android.activities.base.BaseActivity
import com.anonim.android.databinding.ActivityStorageCleanupBinding
import com.anonim.android.frags.storageCleapup.FragStorageCleanupBase
import com.anonim.android.frags.storageCleapup.FragStorageCleanupMain
import com.anonim.android.views.BoldHeader

class ActivityStorageCleanup : BaseActivity() {

    override fun getLayoutResource() = R.layout.activity_storage_cleanup

    override fun shouldInflateAsynchronously() = true

    override fun onActivityInflated(activityView: View, savedInstanceState: Bundle?) {
        val binding = ActivityStorageCleanupBinding.bind(activityView)

        if (savedInstanceState != null) {
            onFragChanged(binding)
        } else {
            initFrag(binding)
        }

        initHeader(binding.header)
        supportFragmentManager.addOnBackStackChangedListener { onFragChanged(binding) }
    }

    private fun initHeader(header: BoldHeader) {
        header.setBGColor(R.color.colorBGPage)
    }

    private fun initFrag(binding: ActivityStorageCleanupBinding) {
        val fm = supportFragmentManager
        val t = fm.beginTransaction()
        t.add(
            R.id.frags_container,
            FragStorageCleanupMain::class.java,
            null,
            FragStorageCleanupMain::class.simpleName
        )
        t.setReorderingAllowed(true)
        t.runOnCommit { onFragChanged(binding) }
        t.commit()
    }

    private fun onFragChanged(binding: ActivityStorageCleanupBinding) {
        val frag = supportFragmentManager.findFragmentById(R.id.frags_container)
        if (frag is FragStorageCleanupBase) {
            frag.setupHeader(this, binding.header)
        }
    }
}
