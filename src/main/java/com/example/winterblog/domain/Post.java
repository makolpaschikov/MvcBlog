package com.example.winterblog.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity-post
 * Stores post id, author, title and text, post creation time
 * @author makolpaschikov
 */
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

    private String title;
    private String text;

    //==========================================
    //============ CONSTRUCTORS ================
    //==========================================

    public Post() {}

    public Post(String title, String text, User author) {
        this.title = title;
        this.text = text;
        this.author = author;

        // Also a crutch, java did not want to cast the date to the format I needed
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String tag) {
        this.title = tag;
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User user) {
        this.author = user;
    }
}
