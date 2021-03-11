package com.example.CRUDpracticeproject2.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class Books {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Date publishDate;

    public Books() {
    }

    @JsonCreator
    public Books(Long id, String name, Date publishDate) {
        this.id = id;
        this.name = name;
        this.publishDate = publishDate;
    }

    @JsonGetter
    public Long getId() {
        return id;
    }

    @JsonSetter
    public void setId(Long id) {
        this.id = id;
    }

    @JsonGetter
    public String getName() {
        return name;
    }

    @JsonSetter
    public void setName(String name) {
        this.name = name;
    }

    @JsonGetter
    public Date getPublishDate() {
        return publishDate;
    }

    @JsonSetter
    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}
