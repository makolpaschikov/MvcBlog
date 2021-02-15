package com.example.winterblog.repository;

import com.example.winterblog.domain.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostDAO extends CrudRepository<Post, String> {
}
