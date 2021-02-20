package com.example.winterblog.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(updatable = false)
    private String creationTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    private String tag;
    private String text;

    //==========================================
    //============ CONSTRUCTORS ================
    //==========================================

    public Post() {}

    public Post(String tag, String text, User author) {
        this.tag = tag;
        this.text = text;
        this.author = author;

        String time = LocalDateTime.now().toString();
        this.creationTime = time.substring(0, time.lastIndexOf(":")).replace('T', ' ');
    }

    //==========================================
    //======= GETTERS AND SETTERS ==============
    //==========================================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User user) {
        this.author = user;
    }
}
