package com.beyond.board.author.dto;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorSaveReqDto {
    private String name;
    private String email;
    private String password;
    
    // 사용자가 String으로 요청해도 Role class로 자동 형변환
    private Role role;

    private PasswordEncoder passwordEncoder;

    public Author toEntity(String passwordEncoder){
        Author author = Author.builder() //Author 객체에 Builder 어노테이션 추가해서 가능해진거임
                .password(passwordEncoder).name(this.name).email(this.email)
                .role(this.role).posts(new ArrayList<>())
                .build();    //마지막에 build 넣어야함(build는 instance method임).
        return author;
    }

}
