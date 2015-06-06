package org.catinthedark.questag;

public interface PicturesRepository {
    void getUrlByTag(final String tagName, final OnLoaded callback);

    interface OnLoaded {
        void run(final ImageModel model);
    }
}
