package org.catinthedark.questag;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QuestionFlow {
    private Iterator<Question> iterator;
    private final QuestionCollection questions;
    private Question current;

    public QuestionFlow() {
        final TagRepository repository = new StaticTagRepository();
        final QuestionGeneratorStrategy strategy = new UniqueQuestionGeneratorStrategy();
        this.questions = strategy.generate(repository.getTagCollection());

        iterator = questions.getQuestions().iterator();
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
