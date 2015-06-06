package org.catinthedark.questag;

import java.util.List;

public interface PicturesRepository {
    void getUrlByTag(final String tagName, final OnLoaded callback);

    interface OnLoaded {
        void run(final List<ImageModel> model);
    }
}
