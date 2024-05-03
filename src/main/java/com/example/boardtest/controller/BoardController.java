package com.example.boardtest.controller;

import com.example.boardtest.dto.BoardDto;
import com.example.boardtest.entity.Board;
import com.example.boardtest.repository.BoardRepository;
import com.example.boardtest.service.BoardService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/")
    public String index(Model model, HttpSession httpSession) {
        List<Board> bl = boardService.findAllBoard();
        model.addAttribute("boardList", bl);

        if (httpSession.getAttribute("username") != null) {
            String username = httpSession.getAttribute("username").toString();
            model.addAttribute("username", username);
        }

        return "index";
    }

    @GetMapping("/board")
    public String board() {
        return "board";
    }

    @PostMapping("/board")
    public String write(BoardDto boardDto, HttpSession httpSession) {
        String title = boardDto.getTitle();
        String content = boardDto.getContent();

        // 세션에 유저네임이 있는 경우(로그인한 상태)만 글 작성
        if (httpSession.getAttribute("username") != null) {
            String username = httpSession.getAttribute("username").toString();
            boardService.saveService(boardDto, username);
        }

        return "redirect:/";
    }

    @GetMapping("/board/{id}")
    public String board(@PathVariable Long id, Model model, HttpSession httpSession) {
        Board selectedBoard = boardService.findBoardById(id);

        if (selectedBoard == null) {
            return "redirect:/";
        }

        if (httpSession.getAttribute("username") != null) {
            model.addAttribute("loginUsername", httpSession.getAttribute("username").toString());
        }

        model.addAttribute("board", selectedBoard);

        return "detail";
    }

    @PostMapping("/board/{id}/edit")
    public String edit(@PathVariable Long id, BoardDto board, Model model) {
        Board editedBoard = boardService.editBoardById(id, board);

        model.addAttribute("board", editedBoard);

        return "redirect:/";
    }

    @GetMapping("/board/{id}/delete")
    public String delete(@PathVariable Long id) {
        boardService.deleteBoardById(id);

        return "redirect:/";
    }
}
