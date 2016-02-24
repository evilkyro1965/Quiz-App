package com.kyrosoft.quiz.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Administrator on 2/18/2016.
 */
@Entity(name="user_quiz_answer")
public class UserQuizAnswer extends UserOwnedEntity {

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="userId")
    private User user;

    @OneToMany(mappedBy = "userQuizAnswer",cascade={CascadeType.MERGE, CascadeType.PERSIST})
    private List<MultipleChoiceQuizAnswer> answers;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="quizId")
    private Quiz quiz;

    @Basic
    private Integer rightAnswer;

    @Basic
    private Integer wrongAnswer;

    public UserQuizAnswer() {}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<MultipleChoiceQuizAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<MultipleChoiceQuizAnswer> answers) {
        this.answers = answers;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Integer getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(Integer rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public Integer getWrongAnswer() {
        return wrongAnswer;
    }

    public void setWrongAnswer(Integer wrongAnswer) {
        this.wrongAnswer = wrongAnswer;
    }
}
