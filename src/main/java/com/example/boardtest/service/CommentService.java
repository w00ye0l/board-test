package com.example.boardtest.service;

import com.example.boardtest.dto.CommentDto;
import com.example.boardtest.entity.Board;
import com.example.boardtest.entity.Comment;
import com.example.boardtest.entity.User;
import com.example.boardtest.repository.BoardRepository;
import com.example.boardtest.repository.CommentRepository;
import com.example.boardtest.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, BoardRepository boardRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    public List<Comment> getCommentByBoardId(Long boardId) {
        return commentRepository.findByBoardId(boardId);
    }

    public void saveComment(Long boardId, CommentDto commentDto, String username) {
        Comment comment = new Comment();

        Board board = boardRepository.findById(boardId).orElse(null);
        User user = userRepository.findById(username).orElse(null);

        if (board != null && user != null) {
            comment.setContent(commentDto.getContent());
            comment.setBoard(board);
            comment.setUser(user);

            commentRepository.save(comment);
        }
    }
}
