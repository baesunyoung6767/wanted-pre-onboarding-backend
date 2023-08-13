package com.example.wanted.User.Service;

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
            // 이미 가입된 회원입니다
        }
    }
}
