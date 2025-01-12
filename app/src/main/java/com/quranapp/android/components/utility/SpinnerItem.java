package com.anonim.android.components.utility;

import com.anonim.android.components.ComponentBase;

public class SpinnerItem extends ComponentBase {
    private CharSequence name;

    public SpinnerItem() {
    }

    public SpinnerItem(CharSequence name) {
        this.name = name;
    }

    public CharSequence getName() {
        return name;
    }

    public SpinnerItem setName(CharSequence name) {
        this.name = name;
        return this;
    }
}
