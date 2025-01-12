package com.anonim.android.frags.storageCleapup

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.anonim.android.R
import com.anonim.android.adapters.storageCleanup.ADPScriptCleanup
import com.anonim.android.components.storageCleanup.ScriptCleanupItemModel
import com.anonim.android.databinding.FragStorageCleanupBinding
import com.anonim.android.utils.reader.QuranScriptUtils
import com.anonim.android.utils.univ.FileUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FragScriptCleanup : FragStorageCleanupBase() {
    private lateinit var fileUtils: FileUtils

    override fun getFragTitle(ctx: Context) = ctx.getString(R.string.strTitleScripts)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frag_storage_cleanup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fileUtils = FileUtils.newInstance(view.context)

        init(FragStorageCleanupBinding.bind(view))
    }

    private fun init(binding: FragStorageCleanupBinding) {
        binding.loader.visibility = View.VISIBLE
        CoroutineScope(Dispatchers.IO).launch {
            val scriptItems = ArrayList<ScriptCleanupItemModel>()

            fileUtils.scriptDir.listFiles()?.forEach { scriptFile ->
                val scriptKey = scriptFile.nameWithoutExtension

                val fontDownloadsCount = QuranScriptUtils.getKFQPCFontDownloadedCount(
                    binding.root.context,
                    scriptKey
                )

                scriptItems.add(
                    ScriptCleanupItemModel(
                        scriptKey = scriptKey,
                        fontDownloadsCount = fontDownloadsCount.second
                    )
                )
            }

            CoroutineScope(Dispatchers.Main).launch {
                populateScripts(binding, scriptItems)
            }
        }
    }

    private fun populateScripts(
        binding: FragStorageCleanupBinding,
        items: List<ScriptCleanupItemModel>
    ) {
        val mAdapter = ADPScriptCleanup(items, fileUtils)
        binding.list.adapter = mAdapter
        binding.list.layoutManager = LinearLayoutManager(binding.root.context)

        binding.loader.visibility = View.GONE
    }
}
