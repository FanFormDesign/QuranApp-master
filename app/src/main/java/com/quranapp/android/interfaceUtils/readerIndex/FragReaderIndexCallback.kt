/*
 * (c) Faisal Khan. Created on 19/2/2022.
 */
package com.anonim.android.interfaceUtils.readerIndex

import android.content.Context

interface FragReaderIndexCallback {
    fun scrollToTop(smooth: Boolean)
    fun sort(ctx: Context)
}