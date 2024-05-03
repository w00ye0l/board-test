package com.example.boardtest.controller;

import com.example.boardtest.dto.CommentDto;
import com.example.boardtest.service.CommentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/board/{boardId}/comment")
    public String comment(@PathVariable Long boardId, CommentDto commentDto, HttpSession httpSession) {
        if (httpSession.getAttribute("username") == null) {
            return "redirect:/board/" + boardId;
        }

        String username = httpSession.getAttribute("username").toString();

        commentService.saveComment(boardId, commentDto, username);

        return "redirect:/board/" + boardId;
    }
}
