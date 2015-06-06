package org.catinthedark.questag;

public interface QuestionGeneratorStrategy {
    QuestionCollection generate(final TagCollection tags);
}
