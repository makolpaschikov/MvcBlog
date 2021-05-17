package com.example.winterblog.service;

import com.example.winterblog.domain.Post;
import com.example.winterblog.domain.User;
import com.example.winterblog.repository.PostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostDAO postDAO;

    @Autowired
    public PostService(PostDAO postDAO) {
        this.postDAO = postDAO;
    }

    public void save(Post post) {
        postDAO.save(post);
    }

    public void deleteAll(List<Post> posts) {
        postDAO.deleteAll(posts);
    }

    public void deleteAll() {
        postDAO.deleteAll();
    }

    public List<Post> getAll() {
        return (List<Post>) postDAO.findAll();
    }

    public List<Post> getByAuthor(User author) {
        return postDAO.findPostByAuthor(author);
    }

    public List<Post> getById(long id) {
        return postDAO.findPostById(id);
    }

    public List<Post> getByAuthorAndFilter(String filter, User user) {
        return postDAO.findPostByTitleIsStartingWithAndAuthor(filter, user);
    }
}
