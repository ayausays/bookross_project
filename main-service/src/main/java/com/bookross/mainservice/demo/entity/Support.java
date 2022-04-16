package com.bookross.mainservice.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

public class Support {

    private Integer id;
    private String supportEmail;
    private String supportType;
    private String supportComment;

    public Support() {

    }

    public Support(Integer id,
                   String supportEmail,
                   String supportType,
                   String supportComment) {
        this.id = id;
        this.supportEmail = supportEmail;
        this.supportType = supportType;
        this.supportComment = supportComment;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSupportEmail() {
        return supportEmail;
    }

    public void setSupportEmail(String supportEmail) {
        this.supportEmail = supportEmail;
    }

    public String getSupportType() {
        return supportType;
    }

    public void setSupportType(String supportType) {
        this.supportType = supportType;
    }

    public String getSupportComment() {
        return supportComment;
    }

    public void setSupportComment(String supportComment) {
        this.supportComment = supportComment;
    }
}
