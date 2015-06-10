package org.catinthedark.questag;

public interface TagRepository {
    void getTagCollection(final OnLoaded onLoaded);

    interface OnLoaded {
        void run(final TagCollection tagCollection);
    }
}
