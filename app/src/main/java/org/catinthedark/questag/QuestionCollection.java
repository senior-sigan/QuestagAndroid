package org.catinthedark.questag;

import java.util.ArrayList;
import java.util.List;

public class QuestionCollection {
    private final List<Question> questions;

    public QuestionCollection() {
        questions = new ArrayList<>();
    }

    public void add(final Question question) {
        questions.add(question);
    }

    public Question get(final Integer i) {
        return questions.get(i);
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
