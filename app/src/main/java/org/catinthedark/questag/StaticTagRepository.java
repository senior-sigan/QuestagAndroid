package org.catinthedark.questag;

import java.util.ArrayList;
import java.util.Collection;

public class StaticTagRepository implements TagRepository {
    final static String[] tags = new String[] {
            "cat", "dog", "flowers", "kavai", "nya", "kitten", "sky", "ocean", "river", "sun", "city"};

    @Override
    public TagCollection getTagCollection() {
        Collection<Tag> tagCollection = new ArrayList<>(10);
        for (String tag: tags) {
            tagCollection.add(new Tag(tag));
        }

        return new TagCollection(tagCollection);
    }
}
