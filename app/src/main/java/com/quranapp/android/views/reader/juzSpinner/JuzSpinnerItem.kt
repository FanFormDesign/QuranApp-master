package com.anonim.android.views.reader.juzSpinner

import com.anonim.android.utils.univ.StringUtils
import com.anonim.android.views.reader.spinner.ReaderSpinnerItem

class JuzSpinnerItem(label: CharSequence) : ReaderSpinnerItem() {
    var juzNumber = -1
    var nameArabic = ""
    var nameTransliterated = ""
        set(nameTransliterated) {
            field = nameTransliterated
            searchKeyword = StringUtils.stripDiacritics(nameTransliterated)
        }

    var searchKeyword: String? = null
        private set

    init {
        super.label = label
    }
}
