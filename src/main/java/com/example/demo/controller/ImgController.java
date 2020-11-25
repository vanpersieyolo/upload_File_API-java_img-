package com.example.demo.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.demo.model.PostImg;
import com.example.demo.repo.IPostRepo;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ImgController {
    @Autowired
    private IPostRepo postRepo;

    String mCloudName = "dtcimirzt";
    String mApiKey = "997964747139867";
    String mApiSecret = "aHfm4-P3L-byZX4H8SQqYUfmZvc";
    Cloudinary cloudinary = new Cloudinary("cloudinary://" + mApiKey + ":" + mApiSecret + "@" + mCloudName);

    @PostMapping("/upload")
    public ResponseEntity<PostImg> createPost(@RequestParam("imageFile") MultipartFile file) {

        try {
            File postImg = Files.createTempFile("temp", file.getOriginalFilename()).toFile();
            file.transferTo(postImg);
            Map responseAV = cloudinary.uploader().upload(postImg, ObjectUtils.emptyMap());
            JSONObject jsonAV = new JSONObject(responseAV);
            String urlAV = jsonAV.getString("url");
            PostImg post = new PostImg();
            post.setImg(urlAV);
            postRepo.save(post);
            return new ResponseEntity<>(post, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
    @GetMapping("/")
    public ResponseEntity<Iterable<PostImg>> showAll(){
        return new ResponseEntity<>(postRepo.findAll(),HttpStatus.OK);
    }
}
