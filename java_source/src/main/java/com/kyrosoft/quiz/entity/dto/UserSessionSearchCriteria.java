package com.kyrosoft.quiz.entity.dto;

/**
 * Created by Administrator on 2/20/2016.
 */
public class UserSessionSearchCriteria extends BaseSearchParameters {

    private Long userId;

    private Long quizId;

    private Boolean isCompleted;

    public UserSessionSearchCriteria() {}

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

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }
}
