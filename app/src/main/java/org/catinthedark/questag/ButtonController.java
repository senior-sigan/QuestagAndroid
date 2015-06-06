package org.catinthedark.questag;

import android.widget.Button;

public class ButtonController {
    private final Button view;
    private Tag model;

    public ButtonController(final Button button) {
        this.view = button;
    }

    public Tag getModel() {
        return model;
    }

    public void setModel(Tag model) {
        this.model = model;
        this.view.setText(model.getName());
    }

    public void setOnClickListener(MainActivity.ButtonListener onClickListener) {
        view.setOnClickListener(onClickListener);
    }
}
