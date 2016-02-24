package com.kyrosoft.quiz.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Administrator on 2/18/2016.
 */
@Entity(name="quiz")
public class Quiz extends UserOwnedEntity {

    @OneToMany(mappedBy = "quiz",cascade={CascadeType.MERGE, CascadeType.PERSIST})
    private List<QuizQuestion> questionList;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="categoryId")
    private QuizCategory category;

    @Basic
    private String name;

    public Quiz() {}

    public List<QuizQuestion> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<QuizQuestion> questionList) {
        this.questionList = questionList;
    }

    public QuizCategory getCategory() {
        return category;
    }

    public void setCategory(QuizCategory category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
