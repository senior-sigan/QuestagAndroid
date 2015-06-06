package org.catinthedark.questag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Generate question collection where each question has difference right answer tag.
 */
public class UniqueQuestionGeneratorStrategy implements QuestionGeneratorStrategy {
    private final static Integer TAGS_COUNT = 4;
    private final Random random = new Random(new Date().getTime());

    @Override
    public QuestionCollection generate(final TagCollection tags) {
        if (tags.size() < TAGS_COUNT) {
            throw new RuntimeException("Not enough tags " + tags.size() + " .Should be more then " + TAGS_COUNT);
        }

        final List<Tag> tagList = tags.getTags();
        Collections.shuffle(tags.getTags());
        final QuestionCollection collection = new QuestionCollection();
        for (int i = 0; i < tagList.size(); ++i) {
            final Set<Tag> variants = new HashSet<>(4);
            variants.addAll(take(tagList, i));
            collection.add(new Question(variants, tagList.get(i)));
        }

        return collection;
    }

    private Set<Tag> take(final List<Tag> tags, final Integer exceptIndex) {
        final Set<Tag> variants = new HashSet<>(4);
        final List<Integer> indexes = new ArrayList<>(tags.size() - 1);
        for (int i = 0; i < tags.size(); ++i) {
            if (i != exceptIndex) {
                indexes.add(i);
            }
        }
        Collections.shuffle(indexes);

        variants.add(tags.get(exceptIndex));
        for (int i = 0; i < TAGS_COUNT - 1; ++i) {
            variants.add(tags.get(indexes.get(i)));
        }

        // JUST to check that all right
        if (variants.size() != TAGS_COUNT) {
            throw new RuntimeException("Wrong tags count. Should be " + TAGS_COUNT + ", instead get " + variants.size());
        }

        return variants;
    }
}
