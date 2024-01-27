package com.example.graduationbe.controller;


import com.example.graduationbe.dto.CommentDto;
import com.example.graduationbe.service.impl.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
@CrossOrigin("*")
public class CommentController {

    private final CommentServiceImpl commentService;

    //them comment
    @PostMapping("/{pId}")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment,
                                                    @PathVariable("pId") Long pId) {
        return ResponseEntity.ok(this.commentService.createComment(comment, pId));
    }

    @GetMapping("/")
    public ResponseEntity<List<CommentDto>> getComments() {
        return ResponseEntity.ok(this.commentService.getComments());
    }

    @GetMapping("/manager-comment")
    public ResponseEntity<List<Map<Object, String>>> getComment1() {
        return ResponseEntity.ok(this.commentService.getComment());
    }

    @GetMapping("/{comId}")
    public ResponseEntity<?> getCommentById(@PathVariable("comId") Long comId) {
        return ResponseEntity.ok(this.commentService.getCommentById(comId));
    }

    @DeleteMapping("/{comId}")
    public void deleteComment(@PathVariable("comId") Long comId) {
        this.commentService.deleteCommentsById(comId);
    }
}
