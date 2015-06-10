package org.catinthedark.questag;

public class TagJson {
    public TagsCollection data;

    public class TagsCollection {
        public String id;
        public String[] tags;
        public String type;
    }
}
