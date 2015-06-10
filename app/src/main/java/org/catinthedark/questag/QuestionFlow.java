package org.catinthedark.questag;

import android.content.Context;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QuestionFlow {
    private Iterator<Question> iterator;
    private QuestionCollection questions;
    private Question current;

    public QuestionFlow(final Context context) {
        final TagRepository repository = new RemoteTagRepository(context);
        final QuestionGeneratorStrategy strategy = new UniqueQuestionGeneratorStrategy();
        repository.getTagCollection(new TagRepository.OnLoaded() {
            @Override
            public void run(final TagCollection tagCollection) {
                questions = strategy.generate(tagCollection);
                iterator = questions.getQuestions().iterator();
            }
        });
    }

    public Question current() {
        return current;
    }

    public Question next() {
        try {
            current = iterator.next();
            return current;
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public Boolean hasNext() {
        return iterator.hasNext();
    }

    public void reset() {
        current = null;
        iterator = questions.getQuestions().iterator();
    }
}
