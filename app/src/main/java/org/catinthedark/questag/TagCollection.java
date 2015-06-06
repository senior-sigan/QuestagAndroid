package org.catinthedark.questag;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TagCollection {
    private final Set<Tag> tags;

    public TagCollection(final Collection<Tag> tags) {
        this.tags = new HashSet<>(tags);
    }

    public int size() {
        return tags.size();
    }

    public List<Tag> getTags() {
        return Arrays.asList(tags.toArray(new Tag[tags.size()]));
    }
}
