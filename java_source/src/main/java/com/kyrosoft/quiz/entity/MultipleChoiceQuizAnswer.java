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
     * No
     */
    @Basic
    private Integer no;

    /**
     * Answer
     */
    @Enumerated(EnumType.STRING)
    private MultipleChoiceAnswer answer;

    /**
     * User Quiz Answer
     */
    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="userQuizAnswerId")
    private UserQuizAnswer userQuizAnswer;

    /**
     * Empty constructor
     */
    public MultipleChoiceQuizAnswer() {}

    /**
     * Get the no
     * @return The no
     */
    public Integer getNo() {
        return no;
    }

    /**
     * Set the no
     * @param no The no
     */
    public void setNo(Integer no) {
        this.no = no;
    }

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

    /**
     * Get the user quiz answer
     * @return The user quiz answer
     */
    public UserQuizAnswer getUserQuizAnswer() {
        return userQuizAnswer;
    }

    /**
     * Set the user quiz answer
     * @param userQuizAnswer The user quiz answer
     */
    public void setUserQuizAnswer(UserQuizAnswer userQuizAnswer) {
        this.userQuizAnswer = userQuizAnswer;
    }
}
