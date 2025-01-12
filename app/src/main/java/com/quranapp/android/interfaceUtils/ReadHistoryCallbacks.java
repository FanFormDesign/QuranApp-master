/*
 * (c) Faisal Khan. Created on 20/11/2021.
 */

package com.anonim.android.interfaceUtils;

import com.anonim.android.components.readHistory.ReadHistoryModel;

public interface ReadHistoryCallbacks {
    void onReadHistoryRemoved(ReadHistoryModel model);

    void onReadHistoryAdded(ReadHistoryModel model);
}
