package com.kyrosoft.quiz.entity.dto;

/**
 * Created by Administrator on 2/18/2016.
 */
public class QuizSearchCriteria extends BaseSearchParameters {

    private Long userId;

    private Long categoryId;

    private String name;

    public QuizSearchCriteria() {}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
