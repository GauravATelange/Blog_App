package com.gaurav.springbootwebApp.service;

import com.gaurav.springbootwebApp.DTO.PostDto;

import java.util.List;

public interface PostService {

    List<PostDto> findAllPosts();

    List<PostDto> findPostsByUser();

    void createPost(PostDto postDto);

    PostDto findPostById(Long postId);

    void updatepost(PostDto postDto);

    void deletePost(Long postId);


    PostDto findPostByUrl(String postUrl);

    List<PostDto> searchPosts(String query);
}
