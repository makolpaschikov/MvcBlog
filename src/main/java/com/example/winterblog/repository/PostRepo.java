package com.example.winterblog.repository;

import com.example.winterblog.domain.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepo extends CrudRepository<Post, String> {
    List<Post> findByTag(String tag);
}
