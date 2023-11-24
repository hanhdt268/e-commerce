package com.example.graduationbe.service.impl;

import com.example.graduationbe.config.JwtAuthenticationFilter;
import com.example.graduationbe.dto.CommentDto;
import com.example.graduationbe.entities.User;
import com.example.graduationbe.entities.commerce.Comment;
import com.example.graduationbe.entities.commerce.Product;
import com.example.graduationbe.repository.CommentRepository;
import com.example.graduationbe.repository.ProductRepository;
import com.example.graduationbe.repository.UserRepository;
import com.example.graduationbe.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;


    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto comment, Long pId) {
        String currentUser = JwtAuthenticationFilter.USER_CURRENT;
        User user = userRepository.findByUsername(currentUser);
        Product product = productRepository.findById(pId).get();
        Comment comment1 = this.modelMapper.map(comment, Comment.class);

        comment1.setProduct(product);
        comment1.setUser(user);
        comment1.setDate(new Date());
        Comment saveComment = this.commentRepository.save(comment1);
        return this.modelMapper.map(saveComment, CommentDto.class);
    }

    @Override
    public List<CommentDto> getComments() {
        List<Comment> comments = this.commentRepository.findAll();
        List<CommentDto> commentDtos = comments.stream().map(comment -> this.modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
        return commentDtos;
    }

    public List<Map<Object, String>> getComment() {
        return this.commentRepository.getComment();
    }

    @Override
    public Comment getCommentById(Long comId) {
        return this.commentRepository.findById(comId).orElse(null);
    }

    @Override
    @Transactional
    public void deleteCommentsById(Long comId) {
        this.commentRepository.deleteCommentByComId(comId);
    }
}
