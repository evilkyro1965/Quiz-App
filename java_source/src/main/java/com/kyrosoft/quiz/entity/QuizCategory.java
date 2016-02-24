package com.kyrosoft.quiz.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;

/**
 * Created by Administrator on 2/18/2016.
 */
@Entity(name="quiz_category")
public class QuizCategory extends IdentifiableEntity {

    @Basic
    private String name;

    public QuizCategory() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
