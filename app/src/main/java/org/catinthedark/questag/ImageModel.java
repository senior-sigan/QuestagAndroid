package org.catinthedark.questag;

public class ImageModel {
    public final String thumbnailUrl;
    public final String highResolutionUrl;

    public ImageModel(final String thumbnailUrl, final String highResolutionUrl) {
        this.thumbnailUrl = thumbnailUrl;
        this.highResolutionUrl = highResolutionUrl;
    }
}
