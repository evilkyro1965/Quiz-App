/*
 * Copyright (C) 2016 Kyrosoft Inc., All Rights Reserved.
 */
package com.kyrosoft.quiz.entity;

import javax.persistence.*;

/**
 * Choice Image
 *
 * @author fahrur
 * @version 1.0
 */
@Entity(name="choice_image")
public class ChoiceImage extends UserOwnedEntity {

    /**
     * Image source
     */
    @Basic
    private String imgSrc;

    /**
     * Question
     */
    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="questionId")
    private QuizQuestion question;

    /**
     * Empty Constructor
     */
    public ChoiceImage() {}

    /**
     * Get the image source
     * @return The image source
     */
    public String getImgSrc() {
        return imgSrc;
    }

    /**
     * Set the image source
     * @param imgSrc The image source
     */
    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    /**
     * Get the question
     * @return The question
     */
    public QuizQuestion getQuestion() {
        return question;
    }

    /**
     * Set the question
     * @param question The question
     */
    public void setQuestion(QuizQuestion question) {
        this.question = question;
    }
}
