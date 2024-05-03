package com.example.boardtest.controller;

import com.example.boardtest.dto.UserDto;
import com.example.boardtest.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(UserDto userDto) {
        userService.saveUser(userDto);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(UserDto userDto, HttpSession httpSession) {
        // 로그인 로직을 실행한 결과를 result에 할당
        String result = userService.login(userDto);

        // result가 fail인 경우는 로그인 실패 -> 다시 로그인 페이지로
        if (result.equals("fail")) {
            return "login";
        }

        // 세션에 결과를 유저네임이라는 키로 저장
        httpSession.setAttribute("username", result);

        // 로그인에 성공하면 메인 페이지로 리다이렉트
        return "redirect:/";
    }
}
