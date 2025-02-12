package com.anonim.android.frags.settings.appLogs

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.anonim.android.R
import com.anonim.android.adapters.appLogs.ADPAppLogs
import com.anonim.android.components.appLogs.AppLogModel
import com.anonim.android.databinding.FragSettingsTranslBinding
import com.anonim.android.frags.BaseFragment
import com.anonim.android.utils.Log
import com.anonim.android.utils.extended.GapedItemDecoration
import com.anonim.android.utils.extensions.dp2px
import com.anonim.android.utils.extensions.updatePaddingHorizontal
import com.anonim.android.utils.univ.DateUtils
import com.anonim.android.utils.univ.FileUtils
import com.anonim.android.widgets.PageAlert

class FragAppLogsCrash : BaseFragment() {
    private lateinit var binding: FragSettingsTranslBinding
    private lateinit var fileUtils: FileUtils

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_settings_transl, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fileUtils = FileUtils.newInstance(view.context)
        binding = FragSettingsTranslBinding.bind(view)

        init(view.context)
    }

    private fun init(context: Context) {
        val logs = ArrayList<AppLogModel>()

        val files = Log.CRASH_ERROR_DIR.listFiles()
        if (files.isNullOrEmpty()) {
            PageAlert(context).apply {
                setIcon(null)
                setMessage("", context.getString(R.string.textNoLogsFound))
                show(binding.container)
            }
            binding.loader.visibility = View.GONE
            return
        }

        files.sortedByDescending { it.lastModified() }.forEach { logFile ->
            val log = logFile.readText()
            val logShort = if (log.length > 200) log.substring(0, 200) + "... ${log.length - 200} more chars" else log

            val parsedDate = DateUtils.toDate(logFile.name, Log.FILE_NAME_DATE_FORMAT)
            val formattedDateTime = if (parsedDate != null) DateUtils.format(parsedDate, DateUtils.DATETIME_FORMAT_USER) else logFile.name

            logs.add(
                    AppLogModel(
                            formattedDateTime,
                            "Fatal Crash",
                            logFile,
                            log,
                            logShort,
                    )
            )
        }

        binding.list.let {
            it.addItemDecoration(GapedItemDecoration(context.dp2px(10F)))
            it.updatePaddingHorizontal(context.dp2px(15F))
            it.layoutManager = LinearLayoutManager(context)
            it.adapter = ADPAppLogs(logs, Color.parseColor("#B23F3F"))
        }
        binding.loader.visibility = View.GONE
    }
}