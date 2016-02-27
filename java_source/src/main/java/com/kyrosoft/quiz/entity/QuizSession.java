package com.kyrosoft.quiz.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2/18/2016.
 */
@Entity(name="quiz_session")
public class QuizSession extends UserOwnedEntity {

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="userId")
    private User user;

    @OneToMany(mappedBy = "quizSession",cascade={CascadeType.MERGE, CascadeType.PERSIST})
    private List<MultipleChoiceQuizAnswer> answers;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="quizId")
    private Quiz quiz;

    @Basic
    private Integer rightAnswer;

    @Basic
    private Integer wrongAnswer;

    @Basic
    private Boolean isCompleted;

    @Basic
    private Date start;

    @Basic
    private Date done;

    public QuizSession() {}

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

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getDone() {
        return done;
    }

    public void setDone(Date done) {
        this.done = done;
    }
}
