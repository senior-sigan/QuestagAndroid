package org.catinthedark.questag;

import android.widget.ImageView;

import com.koushikdutta.ion.Ion;

public class ImageViewController {
    private final ImageView view;
    private ImageModel model;

    public ImageViewController(final ImageView imageView) {
        this.view = imageView;
    }

    public void setModel(final ImageModel model) {
        this.model = model;
        Ion.with(view).load(model.thumbnailUrl);
    }

    public ImageView getView() {
        return view;
    }

    public ImageModel getModel() {
        return model;
    }

    public void setOnClickListener(MainActivity.ThumbImageListener onClickListener) {
        view.setOnClickListener(onClickListener);
    }
}
