/*
 * Copyright (C) 2016 Kyrosoft Inc., All Rights Reserved.
 */
package com.kyrosoft.quiz.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Identifiable Entity
 *
 * @author fahrur
 * @version 1.0
 */
@MappedSuperclass
public abstract class IdentifiableEntity {

    /**
     * Identity
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * Constructor
     */
    public IdentifiableEntity() {}

    /**
     * Get the id
     * @return The id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the id
     * @param id The id
     */
    public void setId(Long id) {
        this.id = id;
    }

}
