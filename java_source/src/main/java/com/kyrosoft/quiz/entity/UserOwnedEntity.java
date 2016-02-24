package com.kyrosoft.quiz.entity;

import javax.persistence.Basic;
import javax.persistence.MappedSuperclass;

/**
 * Created by Administrator on 2/18/2016.
 */
@MappedSuperclass
public abstract class UserOwnedEntity extends IdentifiableEntity {

    @Basic
    private Long userOwnedId;

    public UserOwnedEntity() {}

    public Long getUserOwnedId() {
        return userOwnedId;
    }

    public void setUserOwnedId(Long userOwnedId) {
        this.userOwnedId = userOwnedId;
    }
}
