package com.gaurav.springbootwebApp.service.impl;

import com.gaurav.springbootwebApp.DTO.PostDto;
import com.gaurav.springbootwebApp.entity.Post;
import com.gaurav.springbootwebApp.entity.User;
import com.gaurav.springbootwebApp.mapper.PostMapper;
import com.gaurav.springbootwebApp.repository.PostRepository;
import com.gaurav.springbootwebApp.repository.UserRepository;
import com.gaurav.springbootwebApp.service.PostService;
import com.gaurav.springbootwebApp.util.SecurityUtils;
import com.zaxxer.hikari.metrics.PoolStats;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceimpl implements PostService {

    private PostRepository postRepository;
    private UserRepository userRepository;

    public PostServiceimpl(PostRepository postRepository,UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<PostDto> findAllPosts() {
     List<Post> posts = postRepository.findAll();
     return posts.stream().map( PostMapper :: mapToPostDto)
             .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> findPostsByUser() {
        String email = SecurityUtils.getCurrentUser().getUsername() ;
        User createdBy = userRepository.findByEmail(email);
        Long userId = createdBy.getId();
        List<Post> posts = postRepository.findPostByUser(userId);
        return posts.stream()
                .map((post) -> PostMapper.mapToPostDto(post))
                        .collect(Collectors.toList());
    }

    @Override
    public void createPost(PostDto postDto) {
        String email = SecurityUtils.getCurrentUser().getUsername();

        User user = userRepository.findByEmail(email);
        Post post = PostMapper.maptoPost(postDto);
        post.setCreatedBy(user);
        postRepository.save(post);
    }

    @Override
    public PostDto findPostById(Long postId) {
        Post post = postRepository.findById(postId).get();

        return PostMapper.mapToPostDto(post);
    }

    @Override
    public void updatepost(PostDto postDto) {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User createdBy = userRepository.findByEmail(email);
        Post post = PostMapper.maptoPost(postDto);
        post.setCreatedBy(createdBy);
        postRepository.save(post);
    }

    @Override
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public PostDto findPostByUrl(String postUrl) {
        Post post = postRepository.findByUrl(postUrl).get();
        return PostMapper.mapToPostDto(post);
    }

    @Override
    public List<PostDto> searchPosts(String query) {
        List<Post> posts = postRepository.searchPosts(query);
        return   posts.stream().map(PostMapper::mapToPostDto)
                .collect(Collectors.toList());
    }
}
