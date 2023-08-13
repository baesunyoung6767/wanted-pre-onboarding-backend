package com.example.wanted.User.Repository;

import com.example.wanted.User.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email); // 이메일로 회원을 찾아서 리턴
}
