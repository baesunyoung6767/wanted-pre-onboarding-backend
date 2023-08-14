package com.example.wanted.User.Service;

import com.example.wanted.Exception.AppException;
import com.example.wanted.Exception.ErrorCode;
import com.example.wanted.JwtToken.JwtTokenUtil;
import com.example.wanted.User.Entity.User;
import com.example.wanted.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Value("${jwt.token.secret}")
    private String secretKey;

    @Transactional
    public User registerUser(User user) {
        validateDuplication(user.getEmail());
        return userRepository.save(user);
    }

    public void validateDuplication(String email) {
        User user = userRepository.findByEmail(email);
        if(user != null) {
           throw new AppException(ErrorCode.DUPLICATED_EMAIL); // 이메일이 중복되는 경우
        }
    }

    public String login(String email, String password) {
        User loginUser = userRepository.findByEmail(email);

        // 이메일 입력이 잘못된 경우
        if(loginUser == null) {
            throw new AppException(ErrorCode.USER_NOT_FOUNDED);
        }

        // 비밀번호가 입력이 잘못된 경우
        if(!passwordEncoder.matches(password, loginUser.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD);
        }

        return JwtTokenUtil.createToken(email, secretKey);
    }

    public User getUserByEmail(String email) {
        User foundUser =  userRepository.findByEmail(email);

        if(foundUser == null) {
            throw new AppException(ErrorCode.USER_NOT_FOUNDED);
        }

        return foundUser;
    }
}
