package com.example.demo.service;

import com.example.demo.model.PostImg;
import com.example.demo.repo.IPostRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class PostService implements IPostService {
    @Autowired
    private IPostRepo postRepo;
    @Override
    public Iterable<PostImg> findAll() {
        return postRepo.findAll();
    }

    @Override
    public PostImg save(PostImg postImg) {
        postRepo.save(postImg);
        return postImg;
    }

    @Override
    public Optional<PostImg> findById(Long id) {
        return postRepo.findById(id);
    }
}
