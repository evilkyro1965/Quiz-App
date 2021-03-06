package com.kyrosoft.quiz.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Administrator on 2/18/2016.
 */
@Entity(name="quiz_question")
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class,
        property = "@QuizQuestion")
public class QuizQuestion extends UserOwnedEntity {

    @Basic
    private Integer no;

    @Basic
    @Column(length = 100000)
    private String choiceA;

    @Basic
    @Column(length = 100000)
    private String choiceB;

    @Basic
    @Column(length = 100000)
    private String choiceC;

    @Basic
    @Column(length = 100000)
    private String choiceD;

    @OneToMany(mappedBy = "question",cascade={CascadeType.MERGE, CascadeType.PERSIST})
    private List<ChoiceImage> choiceImageList;

    @Enumerated(EnumType.STRING)
    private MultipleChoiceAnswer correctAnswer;

    @Basic
    @Column(length = 100000)
    private String question;

    @OneToMany(mappedBy = "question")
    private List<QuestionImage> questionImageList;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="quizId")
    private Quiz quiz;

    public QuizQuestion() {}

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public String getChoiceA() {
        return choiceA;
    }

    public void setChoiceA(String choiceA) {
        this.choiceA = choiceA;
    }

    public String getChoiceB() {
        return choiceB;
    }

    public void setChoiceB(String choiceB) {
        this.choiceB = choiceB;
    }

    public String getChoiceC() {
        return choiceC;
    }

    public void setChoiceC(String choiceC) {
        this.choiceC = choiceC;
    }

    public String getChoiceD() {
        return choiceD;
    }

    public void setChoiceD(String choiceD) {
        this.choiceD = choiceD;
    }

    public List<ChoiceImage> getChoiceImageList() {
        return choiceImageList;
    }

    public void setChoiceImageList(List<ChoiceImage> choiceImageList) {
        this.choiceImageList = choiceImageList;
    }

    public MultipleChoiceAnswer getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(MultipleChoiceAnswer correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<QuestionImage> getQuestionImageList() {
        return questionImageList;
    }

    public void setQuestionImageList(List<QuestionImage> questionImageList) {
        this.questionImageList = questionImageList;
    }
}
