package com.example.demo.service;

import com.example.demo.model.PostImg;

import java.util.Optional;

public interface IPostService {
    Iterable<PostImg> findAll();
    PostImg save (PostImg postImg);
    Optional<PostImg> findById(Long id);
}
