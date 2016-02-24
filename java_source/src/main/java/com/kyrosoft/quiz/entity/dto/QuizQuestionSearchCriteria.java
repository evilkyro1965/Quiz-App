package com.kyrosoft.quiz.entity.dto;

/**
 * Created by Administrator on 2/18/2016.
 */
public class QuizQuestionSearchCriteria extends BaseSearchParameters {

    private Long userId;

    private Long quizId;

    public QuizQuestionSearchCriteria() {}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }
}
