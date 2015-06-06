package org.catinthedark.questag;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Question {
    private final Set<Tag> variants;
    private final Tag answer;

    public Question(final Collection<Tag> variants, final Tag answer) {
        this.variants = new HashSet<>(variants);
        this.answer = answer;
    }

    public Set<Tag> getVariants() {
        return variants;
    }

    public Tag getAnswer() {
        return answer;
    }
}
