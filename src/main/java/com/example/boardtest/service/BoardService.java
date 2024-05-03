package com.example.boardtest.service;

import com.example.boardtest.dto.BoardDto;
import com.example.boardtest.entity.Board;
import com.example.boardtest.entity.User;
import com.example.boardtest.repository.BoardRepository;
import com.example.boardtest.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public BoardService(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    public void saveService(BoardDto boardDto, String username) {
        System.out.println("service run");
        Board board = new Board();
        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());

        User user = userRepository.findById(username).orElse(null);
        board.setUser(user);

        boardRepository.save(board);
    }

    public List<Board> findAllBoard() {
        return boardRepository.findAll();
    }

    public Board findBoardById(Long id) {
        return boardRepository.findById(id).orElse(null);
    }

    public Board editBoardById(Long id, BoardDto boardDto) {
        Board board = boardRepository.findById(id).orElse(null);

        if (board == null) {
            return null;
        }

        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());

        boardRepository.save(board);

        return board;
    }

    public void deleteBoardById(Long id) {
        boardRepository.deleteById(id);
    }
}
