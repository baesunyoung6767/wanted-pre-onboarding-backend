package com.example.wanted.User.Controller;

import com.example.wanted.User.Dto.UserDto;
import com.example.wanted.User.Entity.User;
import com.example.wanted.User.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public void registerUser(@Valid @RequestBody UserDto userDto) {
        // 유효성 검사 실패한 경우 예외 처리 필요

        User newUser = userDto.toEntity(passwordEncoder.encode(userDto.getPassword()));
        userService.registerUser(newUser);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/login")
    public String loginUser(@Valid @RequestBody UserDto userDto) {
        // 로그인 성공하면 토큰 넘겨줌
        return userService.login(userDto.getEmail(), userDto.getPassword());
    }
}
