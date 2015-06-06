package org.catinthedark.questag;

import java.io.Serializable;

public class InstagramJson implements Serializable {
    public Media[] data;

    public class Media implements Serializable {
        public String id;
        public String type;
        public String link;
        public Likes likes;
        public Image images;
    }

    public class Likes implements Serializable {
        public Integer count;
    }

    public class Image implements Serializable {
        public ImageData low_resolution;
        public ImageData thumbnail;
        public ImageData standard_resolution;

        public class ImageData implements Serializable {
            public String url;
            public Integer width;
            public Integer height;
        }
    }
}
