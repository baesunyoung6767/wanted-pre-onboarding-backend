package com.example.wanted.User.Dto;

import com.example.wanted.User.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

@Getter @Setter
@AllArgsConstructor // 전체 변수를 생성하는 생성자 생성
@NoArgsConstructor // 기본 생성자 생성
public class UserDto {
    @Email(message = "이메일 형식으로 입력해주세요")
    private String email;

    @Length(min = 8, message = "비밀번호는 8자 이상 입력해주세요")
    private String password;

    public User toEntity(String password) {
        return new User(this.email, password);
    }
}
