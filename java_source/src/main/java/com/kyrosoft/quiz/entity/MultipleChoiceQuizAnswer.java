/*
 * Copyright (C) 2016 Kyrosoft Inc., All Rights Reserved.
 */
package com.kyrosoft.quiz.entity;

import javax.persistence.*;

/**
 * Multiple Choice Quiz Answer
 *
 * @author fahrur
 * @version 1.0
 */
@Entity(name="multiple_choice_quiz_answer")
public class MultipleChoiceQuizAnswer extends UserOwnedEntity {

    /**
     * Answer
     */
    @Enumerated(EnumType.STRING)
    private MultipleChoiceAnswer answer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="questionId")
    private QuizQuestion question;

    /**
     * User Quiz Answer
     */
    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="quizSessionId")
    private QuizSession quizSession;

    /**
     * Empty constructor
     */
    public MultipleChoiceQuizAnswer() {}

    /**
     * Get the answer
     * @return The answer
     */
    public MultipleChoiceAnswer getAnswer() {
        return answer;
    }

    /**
     * Set the answer
     * @param answer The answer
     */
    public void setAnswer(MultipleChoiceAnswer answer) {
        this.answer = answer;
    }

    public QuizSession getQuizSession() {
        return quizSession;
    }

    public void setQuizSession(QuizSession quizSession) {
        this.quizSession = quizSession;
    }

    public QuizQuestion getQuestion() {
        return question;
    }

    public void setQuestion(QuizQuestion question) {
        this.question = question;
    }
}
