package com.anonim.android.adapters.extended

import android.content.Context
import android.view.View
import com.anonim.android.R
import com.anonim.android.utils.extensions.color
import com.anonim.android.utils.extensions.dp2px
import com.anonim.android.utils.extensions.updatePaddingVertical
import com.anonim.android.widgets.list.base.BaseListAdapter
import com.anonim.android.widgets.list.base.BaseListItem
import com.anonim.android.widgets.list.base.BaseListItemView

class PeaceBottomSheetMenuAdapter(context: Context) : BaseListAdapter(context) {
    private val mMessageColor = context.color(R.color.colorText2)

    override fun onCreateItemView(item: BaseListItem, position: Int): View {
        val view = super.onCreateItemView(item, position) as BaseListItemView

        if (item.message.isNullOrEmpty()) {
            view.containerView.updatePaddingVertical(context.dp2px(3f))
        } else {
            view.messageView?.setTextColor(mMessageColor)
        }

        return view
    }
}
