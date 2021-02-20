package com.example.winterblog.repository;

import com.example.winterblog.domain.Post;
import com.example.winterblog.domain.User;
import javafx.geometry.Pos;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostDAO extends CrudRepository<Post, String> {
    List<Post> findPostByAuthor(User author);
    List<Post>  findPostByTagIsStartingWithAndAuthor(String filter, User user);
    void deletePostsByAuthor(User user);
    //void removeAllByAuthor(User user);
}
