package com.example.winterblog.repository;

import com.example.winterblog.domain.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostDAO extends CrudRepository<Post, String> {
    List<Post> findPostByTagStartingWith(String tag);
}
