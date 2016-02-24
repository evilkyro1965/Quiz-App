/*
 * Copyright (C) 2016 Kyrosoft Inc., All Rights Reserved.
 */
package com.kyrosoft.quiz.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2/18/2016.
 */
@Entity(name="question_image")
public class QuestionImage extends UserOwnedEntity {

    @Basic
    private String imgSrc;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="questionId")
    private QuizQuestion question;

    public QuestionImage() {}

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public QuizQuestion getQuestion() {
        return question;
    }

    public void setQuestion(QuizQuestion question) {
        this.question = question;
    }
}
