package com.example.wanted.User.Entity;

import com.example.wanted.User.UserRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column
    private UserRole userRole;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.userRole = UserRole.USER;
    }
}
