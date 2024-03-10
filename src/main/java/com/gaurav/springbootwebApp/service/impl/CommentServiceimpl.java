package com.gaurav.springbootwebApp.service.impl;

import com.gaurav.springbootwebApp.DTO.CommentDto;
import com.gaurav.springbootwebApp.entity.Comment;
import com.gaurav.springbootwebApp.entity.Post;
import com.gaurav.springbootwebApp.entity.User;
import com.gaurav.springbootwebApp.mapper.CommentMapper;
import com.gaurav.springbootwebApp.repository.CommentRepository;
import com.gaurav.springbootwebApp.repository.PostRepository;
import com.gaurav.springbootwebApp.repository.UserRepository;
import com.gaurav.springbootwebApp.service.CommentService;
import com.gaurav.springbootwebApp.util.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceimpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private UserRepository  userRepository;

    public CommentServiceimpl(CommentRepository commentRepository, PostRepository postRepository, UserRepository  userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createComment(String postUrl, CommentDto commentDto) {
        Post post = postRepository.findByUrl(postUrl).get();
        Comment comment = CommentMapper.maptoComment(commentDto);
        comment.setPost(post);
        commentRepository.save(comment);

    }

    @Override
    public List<CommentDto> findAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream()
                .map(CommentMapper::mapToCommentDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public List<CommentDto> findCommentsByPost() {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User createdBy = userRepository.findByEmail(email);
        Long userId = createdBy.getId();
        List<Comment> comments = commentRepository.findCommentsByPost(userId);
         return comments.stream()
                 .map((comment) -> CommentMapper.mapToCommentDto(comment))
                 .collect(Collectors.toList());
    }
}
