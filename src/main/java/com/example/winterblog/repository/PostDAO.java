package com.example.winterblog.repository;

import com.example.winterblog.domain.Post;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostDAO extends CrudRepository<Post, String> {
    List<Post> findPostByTagIsStartingWith(String tag);
}
