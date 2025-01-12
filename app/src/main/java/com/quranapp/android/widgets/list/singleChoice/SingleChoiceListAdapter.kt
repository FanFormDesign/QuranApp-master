package com.anonim.android.widgets.list.singleChoice

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.anonim.android.widgets.list.base.BaseListAdapter
import com.anonim.android.widgets.list.base.BaseListItem
import com.anonim.android.widgets.radio.PeaceRadioButton
import com.anonim.android.widgets.radio.PeaceRadioGroup

class SingleChoiceListAdapter(context: Context) : BaseListAdapter(context) {
    override fun onCreateItemView(item: BaseListItem, position: Int): View {
        val radio = PeaceRadioButton(context).apply {
            tag = item
            setTexts(item.label, item.message)
        }

        if (item.id != View.NO_ID) {
            radio.id = item.id
        }

        return radio
    }

    override fun onAppendItemView(container: ViewGroup, itemView: View, position: Int) {
        super.onAppendItemView(container, itemView, position)

        if (getItem(position).selected && container is PeaceRadioGroup) {
            container.check(itemView.id)
        }
    }
}
