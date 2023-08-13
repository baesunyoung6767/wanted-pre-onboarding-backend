package com.example.wanted.User.Service;

import com.example.wanted.Exception.AppException;
import com.example.wanted.Exception.ErrorCode;
import com.example.wanted.User.Entity.User;
import com.example.wanted.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

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
}
