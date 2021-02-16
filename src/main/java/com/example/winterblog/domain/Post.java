package com.example.winterblog.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String tag;
    private String text;
    private LocalDateTime creationTime;

    //==========================================
    //============ CONSTRUCTORS ================
    //==========================================

    public Post() {}

    public Post(String tag, String text) {
        this.tag = tag;
        this.text = text;
        this.creationTime = LocalDateTime.now();
    }

    //==========================================
    //======= GETTERS AND SETTERS ==============
    //==========================================

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }
}
