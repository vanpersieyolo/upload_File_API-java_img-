package com.example.demo.repo;

import com.example.demo.model.PostImg;
import org.springframework.data.repository.CrudRepository;

public interface IPostRepo extends CrudRepository<PostImg,Long> {
}
