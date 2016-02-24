package com.kyrosoft.quiz.entity.dto;

/**
 * Created by Administrator on 2/20/2016.
 */
public class UserQuizAnswerSearchCriteria extends BaseSearchParameters {

    private Long userId;

    private Long quizId;

    public UserQuizAnswerSearchCriteria() {}

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
