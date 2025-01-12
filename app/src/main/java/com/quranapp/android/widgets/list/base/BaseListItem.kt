package com.anonim.android.widgets.list.base

import android.view.View
import com.anonim.android.components.ComponentBase

class BaseListItem @JvmOverloads constructor(
    var icon: Int = 0,
    var label: String? = null,
    var message: String? = null
) : ComponentBase() {
    var adapter: BaseListAdapter? = null
    var itemView: View? = null
}
