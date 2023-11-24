package com.example.graduationbe.service;

import com.example.graduationbe.dto.CommentDto;
import com.example.graduationbe.entities.commerce.Comment;

import java.util.List;

public interface CommentService {
    CommentDto createComment(CommentDto comment, Long pId);

    List<CommentDto> getComments();

    Comment getCommentById(Long comId);

    void deleteCommentsById(Long comId);
}
