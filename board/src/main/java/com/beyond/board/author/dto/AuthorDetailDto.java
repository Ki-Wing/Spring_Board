package com.beyond.board.author.dto;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.domain.Role;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDetailDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime createdTime;
    private int postCounts;
    private Role role;
    public AuthorDetailDto fromEntity(Author author){
        return AuthorDetailDto.builder()
                .id(author.getId()).name(author.getName()).email(author.getEmail())
                .role(author.getRole()).postCounts(author.getPosts().size())
                .password(author.getPassword()).createdTime(author.getCreatedTime())
                .build();
    }

}


