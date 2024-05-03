package com.example.boardtest.service;

import com.example.boardtest.dto.UserDto;
import com.example.boardtest.entity.User;
import com.example.boardtest.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 회원가입
    public void saveUser(UserDto userDto) {
        // 새로운 유저 객체 생성
        User user = new User();

        // 유저 객체에 유저 DTO 데이터 넣기
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        // 레포지토리에 유저 객체 저장
        userRepository.save(user);
    }

    // 로그인
    public String login(UserDto userDto) {
        // 로그인 검증을 위해 입력한 유저 DTO의 유저네임으로 레포지토리에 해당 유저가 존재하는지 조회
        User user = userRepository.findById(userDto.getUsername()).orElse(null);

        // 조회한 유저가 없을 경우 (회원 정보 자체가 없음)
        if (user == null) {
            return "fail";
        }

        // 조회한 유저가 있지만 입력한 유저 DTO의 비밀번호와 레포지토리(실제 DB)에 저장된 유저 비밀번호가 일치하지 않는 경우
        if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            return "fail";
        }

        // 유저 정보와 비밀번호 모두 일치하는 경우 해당 유저의 유저네임 반환
        return user.getUsername();
    }
}
