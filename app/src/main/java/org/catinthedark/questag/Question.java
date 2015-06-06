package org.catinthedark.questag;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Question {
    private final Set<Tag> variants;
    private final Tag answer;

    public Question(final Collection<Tag> variants, final Tag answer) {
        this.variants = new HashSet<>(variants);
        this.answer = answer;
    }

    public List<Tag> getVariants() {
        return Arrays.asList(variants.toArray(new Tag[variants.size()]));
    }

    public Tag getAnswer() {
        return answer;
    }
}
